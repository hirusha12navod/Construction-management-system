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

public class CalculateFlowSlabController {
    public JFXButton btnCalculateOnAction;
    public JFXButton btnClearOnAction;
    public Label lblSlabSandPan;
    public JFXTextField txtSlabLong;
    public JFXTextField txtSlabHeight;
    public JFXTextField txtSlabWidth;
    public Label lblSlabStonePan;
    public Label lblSlabCementPan;
    private static DecimalFormat dd=new DecimalFormat("#.#");

    Double h,l,w,sand,stone,cement;

    public void sandCal(){
        sand=((h*l*w)/(4*48*48))*12;
        lblSlabSandPan.setText(String.valueOf((dd.format(sand))));
    }

    public void cementCal(){
        cement=((h*l*w)/(4*48*48))*5;
        lblSlabCementPan.setText(String.valueOf((dd.format(cement))));
    }

    public void stoneCal(){
        stone=((h*l*w)/(4*48*48))*16;
        lblSlabStonePan.setText(String.valueOf((dd.format(stone))));
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        lblSlabCementPan.setText("");
        lblSlabSandPan.setText("");
        lblSlabStonePan.setText("");
        txtSlabHeight.setText("");
        txtSlabLong.setText("");
        txtSlabWidth.setText("");
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        h=Double.parseDouble(txtSlabHeight.getText());
        l=Double.parseDouble(txtSlabLong.getText());
        w=Double.parseDouble(txtSlabWidth.getText());

        w*=12;
        l*=12;

        sandCal();
        cementCal();
        stoneCal();
    }


    public JFXButton back;
    public AnchorPane slabPane;
    String SerialId="";


    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) slabPane.getScene().getWindow();
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

