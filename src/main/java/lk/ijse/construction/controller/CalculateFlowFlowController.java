package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;
import java.text.DecimalFormat;

public class CalculateFlowFlowController {
    public JFXButton btnCalculateOnAction;
    public JFXButton btnClearOnAction;
    public Label lblFlowSandPan;
    public JFXTextField txtFlowLong;
    public JFXTextField txtFlowHeight;
    public JFXTextField txtFlowWidth;
    public Label lblFlowStonePan;
    public Label lblFlowCementPan;
    private static DecimalFormat dd=new DecimalFormat("#.#");

    Double h, l, w, sand, stone, cement;

    public void btnClearOnAction(ActionEvent actionEvent) {
        lblFlowCementPan.setText("");
        lblFlowSandPan.setText("");
        lblFlowStonePan.setText("");
        txtFlowHeight.setText("");
        txtFlowLong.setText("");
        txtFlowWidth.setText("");
    }

    public void sandCal() {
        sand = ((h * l * w) / (2 * 48 * 48)) * 2.5;
        lblFlowSandPan.setText(String.valueOf((dd.format(sand))));
    }

    public void cementCal() {
        cement = ((h * l * w) / (2 * 48 * 48)) * 4;
        lblFlowCementPan.setText(String.valueOf((dd.format(cement))));
    }

    public void stoneCal() {
        stone = ((h * l * w) / (2 * 48 * 48)) * 5;
        lblFlowStonePan.setText(String.valueOf((dd.format(stone))));
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        h = Double.parseDouble(txtFlowHeight.getText());
        l = Double.parseDouble(txtFlowLong.getText());
        w = Double.parseDouble(txtFlowWidth.getText());

        w*=12;
        l*=12;

        sandCal();
        cementCal();
        stoneCal();
    }


    public JFXButton back;
    public AnchorPane FlowPane;
    String SerialId = "";



    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) FlowPane.getScene().getWindow();
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
