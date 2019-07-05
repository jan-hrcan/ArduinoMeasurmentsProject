package net.jan.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.layout.HBox;
import net.jan.plotter.PlotterLux;

import java.util.Scanner;

/**
 * Created by ElFuego on 5/21/2017.
 */
public class SerialDataTransferLux extends Thread {

    private SerialPort selectedPort;
    private SerialPort[] selectedPorts;
    private double number;
    private int temp = 0;
    private Scanner scanner;
    private PlotterLux plotterLux;
    private Object lock;

    public SerialDataTransferLux(SerialPort input, HBox root, Object l) {
        plotterLux = new PlotterLux(root);
        selectedPort = input;
        scanner = new Scanner(selectedPort.getInputStream());
        lock = l;
    }



    @Override
    public void run() {
        synchronized (lock){
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == 'P') {
                line = line.replaceAll("P=","");
                number = Double.parseDouble(line);
                System.out.print(line + "!!! !!!" + '\n');
                plotterLux.updatePlotLux(number);
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
