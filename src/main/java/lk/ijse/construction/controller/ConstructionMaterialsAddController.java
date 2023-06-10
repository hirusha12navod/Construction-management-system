package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.bo.BoFactory;
import lk.ijse.construction.bo.custom.ConstructionBo;

import java.io.IOException;
import java.sql.SQLException;

public class ConstructionMaterialsAddController {
    public Label lblStock;
    public JFXComboBox cmbMaterial;
    public TextField txtQty;


    public JFXButton back;
    public AnchorPane materialsAddPane;
    String SerialId="";

//    ConstructionDao constructionDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CONSTRUCTION_DAO);
    ConstructionBo constructionBo = BoFactory.getInstance().getBo(BoFactory.BoType.CONSTRUCTION_BO);

    @FXML
    public void initialize(){
        loadCategory();

            back.setOnMouseClicked(mouseEvent -> {
                Stage stage = (Stage) materialsAddPane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    private void loadCategory() {
        ObservableList<String> list = FXCollections.observableArrayList("Sand","Concrete Bricks","Clay Bricks","Cements","Rock Stones","Concrete Stones");
        cmbMaterial.setItems(list);

        cmbMaterial.setOnAction(actionEvent -> {
            try {
                lblStock.setText(String.valueOf(constructionBo.getStock(cmbMaterial.getValue().toString())));
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        makanna();
    }
    public void makanna(){
        cmbMaterial.setValue("");
        lblStock.setText(null);
        txtQty.clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().isEmpty() && !cmbMaterial.getValue().toString().isEmpty()){
            try {
                constructionBo.updateStock(Double.parseDouble(lblStock.getText()) + Double.parseDouble(txtQty.getText()),cmbMaterial.getValue().toString());
                lblStock.setText(String.valueOf(constructionBo.getStock(cmbMaterial.getValue().toString())));
            }catch (SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        makanna();
    }
}
