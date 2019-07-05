package net.jan.serial;

import com.fazecast.jSerialComm.SerialPort;
import javafx.scene.layout.HBox;
import net.jan.plotter.PlotterTemp;

import java.util.Scanner;

public class SerialDataTransferTemperature extends Thread{

    private SerialPort selectedPort;
    private SerialPort[] selectedPorts;
    private double number;
    private int temp = 0;
    private Scanner scanner;
    private PlotterTemp plotterTemp;
    private Object lock;

    public SerialDataTransferTemperature(SerialPort input, HBox root, Object l) {
        plotterTemp = new PlotterTemp(root);
        selectedPort = input;
        scanner = new Scanner(selectedPort.getInputStream());
        lock = l;
    }



    @Override
    public void run() {
        synchronized (lock) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.charAt(0) == 'T') {
                    line = line.replaceAll("T=", "");
                    number = Double.parseDouble(line);
                    System.out.print(line + "!" + '\n');
                    plotterTemp.updatePlotTemp(number);
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
