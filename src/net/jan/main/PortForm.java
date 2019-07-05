package net.jan.main;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.jan.serial.SerialConnection;

public class PortForm extends BorderPane{

    // layouts
    private VBox top;
    private HBox buttonContainer;
    private AnchorPane middle;

    // table controls
    private TableView<SerialPort> tableView;
    private TableColumn<SerialPort, String> columnSystemPortName;
    private TableColumn<SerialPort, String> columnDescriptivePortName;
    private TableColumn<SerialPort, Integer> columnBaudRate;
    private TableColumn<SerialPort, Boolean> columnIsOpened;

    // other controls
    private Button buttonConfirm;
    private Button buttonCancel;

    // objects
    private ObservableList<SerialPort> serialConnection = FXCollections.observableArrayList();

    public PortForm(ObservableList<SerialPort> sc){
        serialConnection = sc;

        top = new VBox(5);
        buttonContainer = new HBox(3);
        middle = new AnchorPane();

        buttonConfirm = new Button();
        buttonCancel = new Button();

        columnSystemPortName.setCellValueFactory(new PropertyValueFactory<SerialPort, String>("getSystemPortName"));
        columnDescriptivePortName.setCellValueFactory(new PropertyValueFactory<SerialPort, String>("portString"));
        columnBaudRate.setCellValueFactory(new PropertyValueFactory<SerialPort, Integer>("baudRate"));
        columnIsOpened.setCellValueFactory(new PropertyValueFactory<SerialPort, Boolean>("isOpened"));

        tableView = new TableView<>();

        tableView.getColumns().addAll(columnSystemPortName, columnDescriptivePortName, columnBaudRate, columnIsOpened);

        tableView.setItems(serialConnection);

        middle.getChildren().add(tableView);
        buttonContainer.getChildren().addAll(buttonConfirm, buttonCancel);
        top.getChildren().add(buttonContainer);
        this.setTop(top);
        this.setCenter(middle);


    }
}
