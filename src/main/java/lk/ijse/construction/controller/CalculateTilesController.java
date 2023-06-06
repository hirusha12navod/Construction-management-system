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
import java.nio.DoubleBuffer;
import java.text.DecimalFormat;
import java.util.Properties;

public class CalculateTilesController {
    public JFXButton btnCalculateOnAction;
    public JFXButton btnClearOnAction;
    public JFXTextField txtFlowWidth;
    public JFXTextField txtFlowLong;
    public JFXTextField txtTileLong;
    public Label lblNumberOfTiles;
    public JFXTextField txtTileWidth;
    private static DecimalFormat dd=new DecimalFormat("#.#");

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtFlowLong.setText("");
        txtFlowWidth.setText("");
        txtTileLong.setText("");
        txtTileWidth.setText("");
        lblNumberOfTiles.setText("");
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        Double tile,tl,tw,w,l;
        l=Double.parseDouble(txtFlowLong.getText());
        w=Double.parseDouble(txtFlowWidth.getText());
        tl=Double.parseDouble(txtTileLong.getText());
       tw=Double.parseDouble(txtTileWidth.getText());

       tile=(l*w)/(tl*tw);
       lblNumberOfTiles.setText(String.valueOf(dd.format(tile)));
    }

    public JFXButton back;
    public AnchorPane tilesPane;
    String SerialId="";

    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) tilesPane.getScene().getWindow();
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
