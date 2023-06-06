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

public class CalculateWallController {
    public JFXButton btnCalculateOnAction;
    public JFXButton btnClearOnAction;
    public JFXTextField txtTowerLong;
    public JFXTextField txtTowerHeight;
    public Label lblTowerStonePan;
    private static DecimalFormat dd=new DecimalFormat("#.#");

    Double l,h,brick;

    public void btnClearOnAction(ActionEvent actionEvent) {
        lblTowerStonePan.setText("");
        txtTowerHeight.setText("");
        txtTowerLong.setText("");
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        l=Double.parseDouble(txtTowerLong.getText());
        h=Double.parseDouble(txtTowerHeight.getText());
        l*=12;
        h*=12;
        brick=(l*h)/(6.5*14);
        lblTowerStonePan.setText(String.valueOf(dd.format(brick)));
    }

    public JFXButton back;
    public AnchorPane wallPane;
    String SerialId="";

    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) wallPane.getScene().getWindow();
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
