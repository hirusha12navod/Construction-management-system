package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.model.tm.ItemTM;
import lk.ijse.construction.model.ItemsDto;
import lk.ijse.construction.dao.custom.impl.HardwareItemAddDaoImpl;
import lk.ijse.construction.dao.custom.impl.ItemListDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HardwareItemListController {
    public AnchorPane itemListPane;
    public JFXButton back;

    String SerialId="";

    public JFXComboBox cmbICategory;

    public TableColumn colRackNo;
    public TableView<ItemTM> tblItems;
    public TableColumn colIID;
    public TableColumn colIName;
    public TableColumn colInitialStock;
    public TableColumn calPrice;

    private void loadItemCatogories() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = HardwareItemAddDaoImpl.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbICategory.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setCellValueFactory() {
        colIID.setCellValueFactory(new PropertyValueFactory<>("item_Id"));
        colIName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colInitialStock.setCellValueFactory(new PropertyValueFactory<>("initial_stock"));
        calPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colRackNo.setCellValueFactory(new PropertyValueFactory<>("rack_no"));
    }

    @FXML
    void cmbICategoryOnAction(ActionEvent actionEvent){
        String id=String.valueOf(cmbICategory.getValue());

        try {
            ObservableList<ItemTM> obList = FXCollections.observableArrayList();
            List<ItemsDto> IList = ItemListDaoImpl.getAll(id);

            for (ItemsDto itm : IList) {
                obList.add(new ItemTM(
                        itm.getItem_Id(),
                        itm.getItem_name(),
                        itm.getInitial_stock(),
                        itm.getPrice(),
                        itm.getRack_no()
                ));
            }
            tblItems.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
        setCellValueFactory();
    }

    @FXML
    void initialize(){

        loadItemCatogories();

            back.setOnMouseClicked(mouseEvent -> {
                Stage stage = (Stage) itemListPane.getScene().getWindow();
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
