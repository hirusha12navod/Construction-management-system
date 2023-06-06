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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.ItemCall;
import lk.ijse.construction.dto.ItemL;
import lk.ijse.construction.dto.SupName;
import lk.ijse.construction.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HardwareItemUpdateController {
    public AnchorPane updateItemsPane;
    public JFXButton back;
    public Label lblItemId;
    public JFXComboBox cmbItemCategory;
    public JFXButton btnUpdateOnAction;
    public JFXButton btnClearOnAction;
    public JFXComboBox cmbItem;
    public JFXComboBox cmbSupplier;
    public Label lblDate;
    public TextField txtQty;

    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) updateItemsPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loadCatIds();
        LocalDate today=LocalDate.now();
        lblDate.setText(String.valueOf(today));
    }

    private void loadCatIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = HardwareItemAddModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbItemCategory.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    public void cmbItemCategoryOnAction(ActionEvent actionEvent) {
        String id=String.valueOf(cmbItemCategory.getValue().toString());
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<ItemL> ids = ItemNameTakeModel.getList(id);

            for (ItemL idm : ids) {
                obList.add(idm.getItem_name());
            }
            cmbItem.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String cat = String.valueOf(cmbItemCategory.getValue());
        String itname = String.valueOf(cmbItem.getValue());
        String itId = lblItemId.getText();
        String supp = String.valueOf(cmbSupplier.getValue());
        String dt =lblDate.getText();
        String qty = txtQty.getText();

        try {
            Connection con = DBconnection.getInstance().getConnection();
            String queSave = "INSERT INTO supply(supplier_name, item_category, item_Id, item_name, qty, Date) VALUES(?,?,?,?,?,?)";
            PreparedStatement stSave = con.prepareStatement(queSave);
            stSave.setString(1, supp);
            stSave.setString(2, cat);
            stSave.setString(3, itId);
            stSave.setString(4, itname);
            stSave.setString(5, qty);
            stSave.setString(6, dt);
            stSave.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION, "Stock Updated Successfully!").show();
            makanna();
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String id=String.valueOf(cmbItem.getValue());

        try {
            if (cmbItem.getValue()!=null) {
                ItemCall IC = ItemNameTakeModel.searchById(id);
                lblItemId.setText(IC.getItem_Id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!"+e).show();
        }

        String sup=String.valueOf(cmbItem.getValue());
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<SupName> ids = SupplierNameModel.getList(sup);

            for (SupName idm : ids) {
                obList.add(idm.getSupplier_name());
            }
            cmbSupplier.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        makanna();
    }

    private void makanna(){
        cmbItem.setValue("");
        lblItemId.setText("");
        cmbSupplier.setValue("");
        txtQty.setText("");
        cmbItemCategory.setValue("--SELECT--");
        cmbItemCategory.requestFocus();
    }

    public void cmbSupplierOnAction(ActionEvent actionEvent) {
    }
}
