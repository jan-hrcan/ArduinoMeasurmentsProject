package net.jan.main;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.jan.serial.SerialConnection;

public class PortFormController {

    @FXML
    private Button buttonConfirm;
    @FXML
    private Button buttonCancel;
    @FXML
    private TableView<SerialPort> tableView;
    @FXML
    private TableColumn<SerialPort, String> columnSystemPortName;
    @FXML
    private TableColumn<SerialPort, String> columnDescriptivePortName;
    @FXML
    private TableColumn<SerialPort, Integer> columnBaudRate;
    @FXML
    private TableColumn<SerialPort, Boolean> columnIsOpened;

    // non-fxml fields
    private SerialConnection sc;

    @FXML
    private void initialize(){

    }

    @FXML
    void buttonCancelOnAction(ActionEvent event) {

    }

    @FXML
    void buttonConfirmOnAction(ActionEvent event) {

    }

}
