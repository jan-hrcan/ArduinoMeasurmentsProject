package net.jan.main;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.jan.plotter.PlotterTemp;
import net.jan.serial.SerialConnection;
import net.jan.serial.SerialDataTransferHumidity;
import net.jan.serial.SerialDataTransferLux;
import net.jan.serial.SerialDataTransferTemperature;

public class Main extends Application {

    private HBox hBox;
    private BorderPane root;
    private SerialConnection sc;
    private Button button;
    private ComboBox<String> comboBox;
    private SerialPort chosenPort;

    // Plotters
    private SerialDataTransferTemperature sdtt;
    private SerialDataTransferHumidity sdth;
    private SerialDataTransferLux sdtl;

    // Mutex
    private Object lock;

    // Menu bar, menus and menu items
    private MenuBar menuBar;
    private Menu menuStart;
    private MenuItem menuItemPort;


    @Override
    public void start(Stage primaryStage) throws Exception{
        // menu objects initialization
        menuBar = new MenuBar();
        menuStart = new Menu("Start");
        menuItemPort = new MenuItem("Choose port");

        menuBar.getMenus().add(menuStart);
        menuStart.getItems().add(menuItemPort);

        lock = new Object();

        hBox = new HBox(5);
        sc = new SerialConnection();

        button = new Button("CONNECT!");

        comboBox = new ComboBox(sc.getObservablePortList());


        try {
            root = new BorderPane();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/net/jan/main/app.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

        root.setTop(menuBar);
        root.setCenter(hBox);
        root.setTop(new HBox(comboBox, button));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.print("Button fired!");
                if (button.getText() == "CONNECT!") {
                    chosenPort = SerialPort.getCommPort(comboBox.getSelectionModel().getSelectedItem().toString());
                    chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                    if (chosenPort.openPort()) {
                        button.setText("DISCONNECT!");
                        comboBox.setDisable(true);
                    }

                    sdtt = new SerialDataTransferTemperature(chosenPort, hBox, lock);
                    sdth = new SerialDataTransferHumidity(chosenPort, hBox, lock);
                    sdtl = new SerialDataTransferLux(chosenPort, hBox, lock);

                    sdtt.start();
                    try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                    sdth.start();
                    try{Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                    sdtl.start();


                } else if (button.getText() == "DISCONNECT!") {
                    button.setText("CONNECT!");
                    sdtt.suspend();
                    sdth.suspend();
                    sdtl.suspend();
                    chosenPort.closePort();
                    comboBox.setDisable(false);
                }


            }
            });




    }


    public static void main(String[] args) {
        launch(args);
    }


}
