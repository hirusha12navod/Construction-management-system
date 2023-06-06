package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.dto.MaterialTm;
import lk.ijse.construction.model.ConstructionModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ConstructionMaterialsListController {

    public TableView<MaterialTm> tblMaterial;
    public TableColumn clmMaterialName;
    public TableColumn clmMaterilStock;


    public JFXButton back;
    public AnchorPane materialsListPane;
    String SerialId="";

    @FXML
    public void initialize(){
        clmMaterialName.setCellValueFactory(new PropertyValueFactory<>("material_name"));
        clmMaterilStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        try {
            ObservableList<MaterialTm> all = ConstructionModel.getAll();
            tblMaterial.setItems(all);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) materialsListPane.getScene().getWindow();
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
