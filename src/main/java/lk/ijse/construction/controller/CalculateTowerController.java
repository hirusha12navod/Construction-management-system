package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;

public class CalculateTowerController {
    public JFXButton btnCalculateOnAction;
    public JFXButton btnClearOnAction;
    public Label lblTowerSandPan;
    public JFXTextField txtTowerLong;
    public JFXTextField txtTowerHeight;
    public JFXTextField txtTowerWidth;
    public Label lblTowerStonePan;
    public Label lblTowerCementPan;
    private static DecimalFormat dd=new DecimalFormat("#.#");

    Double h,l,w,sand,stone,cement;

    public void btnClearOnAction(ActionEvent actionEvent) {
        lblTowerCementPan.setText("");
        lblTowerSandPan.setText("");
        lblTowerStonePan.setText("");
        txtTowerHeight.setText("");
        txtTowerLong.setText("");
        txtTowerWidth.setText("");
    }

    public void sandCal(){
        sand=((h*l*w)/(72*9*9))*6;
        lblTowerSandPan.setText(String.valueOf((dd.format(sand))));
    }

    public void cementCal(){
        cement=((h*l*w)/(72*9*9))*3;
        lblTowerCementPan.setText(String.valueOf((dd.format(cement))));
    }

    public void stoneCal(){
        stone=((h*l*w)/(72*9*9))*8;
        lblTowerStonePan.setText(String.valueOf((dd.format(stone))));
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        h=Double.parseDouble(txtTowerHeight.getText());
        l=Double.parseDouble(txtTowerLong.getText());
        w=Double.parseDouble(txtTowerWidth.getText());

        h*=12;

        sandCal();
        cementCal();
        stoneCal();
    }

    public JFXButton back;
    public AnchorPane towerPane;
    String SerialId="";

    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) towerPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
