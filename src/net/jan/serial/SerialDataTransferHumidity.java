package net.jan.serial;

import com.fazecast.jSerialComm.SerialPort;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import net.jan.plotter.PlotterHum;

import java.util.Scanner;

public class SerialDataTransferHumidity extends Thread {

    private SerialPort selectedPort;
    private SerialPort[] selectedPorts;
    private double number;
    private Scanner scanner;
    private PlotterHum plotterHum;
    private int temp = 0;
    private Object lock;


    public SerialDataTransferHumidity(SerialPort input, HBox root, Object l) {
        plotterHum = new PlotterHum(root);
        selectedPort = input;
        scanner = new Scanner(selectedPort.getInputStream());
        lock = l;
    }



    @Override
    public void run() {
        synchronized (lock){
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == 'H') {
                line = line.replaceAll("H=", "");
                number = Double.parseDouble(line);
                System.out.print(line + "!!! " + '\n');
                plotterHum.updatePlotHum(number);
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

            }
        }



}
