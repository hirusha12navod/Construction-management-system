package lk.ijse.construction.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    public Button btnHCROnAction1;
    public Button btnCalculateManageOnAction;
    public Button btnEmployeeManageOnAction;
    public Button btnConstructionManageOnAction;
    public Button btnHardwareManageOnAction;
    public Button btnCROnAction;


    @FXML
    private AnchorPane root;

    @FXML
    void btnCROnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/HardwareCustomerReg.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Registration");
        stage.centerOnScreen();
    }

    public void initialize(){
        btnCROnAction.setVisible(false);
    }

    public void btnHardwareManageOnAction(ActionEvent actionEvent){
        btnCROnAction.setVisible(true);
    }

    public void btnConstructionManageOnAction(ActionEvent actionEvent){
        btnCROnAction.setVisible(false);
    }

}
