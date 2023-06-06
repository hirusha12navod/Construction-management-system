package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.bo.custom.BoFactory;
import lk.ijse.construction.bo.custom.ConstructionBo;
import lk.ijse.construction.model.MaterialDto;
import lk.ijse.construction.model.tm.MaterialTm;

import java.io.IOException;
import java.sql.SQLException;

public class ConstructionMaterialsListController {

    public TableView<MaterialTm> tblMaterial;
    public TableColumn clmMaterialName;
    public TableColumn clmMaterilStock;


    public JFXButton back;
    public AnchorPane materialsListPane;
    String SerialId="";

//    ConstructionDao constructionDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CONSTRUCTION_DAO);
    ConstructionBo constructionBo = BoFactory.getInstance().getBo(BoFactory.BoType.CONSTRUCTION_BO);

    @FXML
    public void initialize(){
        clmMaterialName.setCellValueFactory(new PropertyValueFactory<>("material_name"));
        clmMaterilStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        try {
            ObservableList<MaterialTm> all = FXCollections.observableArrayList();
            for (MaterialDto material:constructionBo.getAll()) {
                all.add(new MaterialTm(
                        material.getMaterial_name(),
                        material.getStock()
                ));
            }
            tblMaterial.setItems(all);
        } catch (SQLException | ClassNotFoundException throwables) {
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
