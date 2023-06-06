package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.model.ItemLDto;
import lk.ijse.construction.dao.custom.impl.HardwareItemAddDaoImpl;
import lk.ijse.construction.dao.custom.impl.ItemListDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HardwareItemSearchController {
    public Label lblPrice;
    public JFXComboBox cmbCat;
    public JFXComboBox cmbItem;
    public Label lblRack;
    public AnchorPane searchItemspane;
    public JFXButton back;

    @FXML
    public void initialize(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = HardwareItemAddDaoImpl.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbCat.setItems(obList);

            cmbCat.setOnAction(actionEvent -> {
                try {
                    ObservableList<String> list = FXCollections.observableArrayList();
                    List<ItemLDto> names = ItemListDaoImpl.getList(cmbCat.getValue().toString());

                    for (ItemLDto idm : names) {
                        list.add(idm.getItem_name());
                    }
                    cmbItem.setItems(list);

                    cmbItem.setOnAction(actionEvent1 -> {
                        try {
                            lblPrice.setText(String.valueOf(ItemListDaoImpl.getPrice(cmbItem.getValue().toString())));
                            lblRack.setText(String.valueOf(ItemListDaoImpl.getRack(cmbItem.getValue().toString())));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) searchItemspane.getScene().getWindow();
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
