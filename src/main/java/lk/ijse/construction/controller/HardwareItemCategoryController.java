package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class HardwareItemCategoryController {



    public JFXButton btnClearOnAction;
    public JFXButton btnSaveOnAction;
    public JFXTextField txtCateName;
    public AnchorPane itemCategoryPane;
    public JFXButton back;


    public void btnSaveOnAction(ActionEvent actionEvent){
        String cate=txtCateName.getText();

        try {
            Connection con = DBconnection.getInstance().getConnection();
            String queSave = "INSERT INTO category(item_category) VALUES(?)";
            PreparedStatement stSave = con.prepareStatement(queSave);
            stSave.setString(1, cate);
            stSave.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION, "Item Category: " + cate + "Saved Successfully!").show();
            makanna();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) itemCategoryPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void makanna(){

        txtCateName.setText(" ");
    }
    public void btnClearOnAction(ActionEvent actionEvent){

        makanna();
    }
}
