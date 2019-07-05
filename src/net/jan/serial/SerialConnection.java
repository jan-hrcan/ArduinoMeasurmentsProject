package net.jan.serial;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SerialConnection {

    private ObservableList<String> observablePortList = FXCollections.observableArrayList();
    private SerialPort[] ports;

    public SerialConnection(){
        ports = SerialPort.getCommPorts();
        for (SerialPort port: ports){
            observablePortList.add(port.getSystemPortName());
        }
    }


    public ObservableList<String> getObservablePortList() {
        return observablePortList;
    }

    public void setObservablePortList(ObservableList<String> observablePortList) {
        this.observablePortList = observablePortList;
    }

    public SerialPort[] getPorts() {
        return ports;
    }

    public void setPorts(SerialPort[] ports) {
        this.ports = ports;
    }
}
