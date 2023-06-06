package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.Employee;
import lk.ijse.construction.dto.SupplierTm;
import lk.ijse.construction.model.EmployeeRegistrationModel;
import lk.ijse.construction.model.ItemListModel;
import lk.ijse.construction.model.SupplierModel;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class HardwareSupplierRegController {


    public TableView<SupplierTm> tblSupplier;
    public TableColumn clmSupplierId;
    public TableColumn clmSupplierName;
    public TableColumn clmContact;
    public TableColumn clmItem;
    public AnchorPane SupplierRegistrationPane;
    public JFXButton back;
    String SerialId="";


    public Label lblSupplierId;
    public JFXButton btnUpdateOnAction;
    public JFXButton btnClearOnAction;
    public TextField txtSupplierContact;
    public JFXTextField txtSupplierName;
    public JFXComboBox cmbItemOnAction;
    public JFXButton btnSaveOnAction;

    ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();
    @FXML
    void initialize(){

        clmSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        clmSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        idGen();
        btnUpdateOnAction.setDisable(true);

        try {
            cmbItemOnAction.setItems(ItemListModel.getAll());
        }catch (SQLException e){
            e.printStackTrace();
        }
        loadAllSuppliers();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) SupplierRegistrationPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        /*cmbEmployeeId.setVisible(false);
        btnupdate.setVisible(false);*/
    }

    private void setData(SupplierTm newValue) {
        btnUpdateOnAction.setDisable(false);
        lblSupplierId.setText(newValue.getSupplier_id());
        txtSupplierName.setText(newValue.getName());
        txtSupplierContact.setText(String.valueOf(newValue.getContact()));
        cmbItemOnAction.setValue(newValue.getItem());
    }

    private void loadAllSuppliers() {
        try {
            tmList = SupplierModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblSupplier.setItems(tmList);
    }

    /*@FXML
    void cmbSupplierIdOnAction(ActionEvent actionEvent){
        String id=String.valueOf(cmbSupplierId.getValue());

        try {
            Employee employee = EmployeeRegistrationModel.searchById(id);
            txtfname.setText(employee.getEmpName());
            txtdesignation.setText(employee.getDesignation());
            txtcontactno.setText(employee.getContact_no());
            txtnic.setText(employee.getNic());
            txtlane.setText(employee.getEAddress());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }*/

    public void idGen(){
        try{
            Connection con= DBconnection.getInstance().getConnection();
                String queMax="SELECT supplier_Id FROM supplier ORDER BY supplier_Id DESC LIMIT 1";
                PreparedStatement preparedStatement = con.prepareStatement(queMax);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String id = resultSet.getString(1).split("-")[1];
                    int num = Integer.parseInt(id);
                    lblSupplierId.setText(String.format("S-%04d", ++num));
                }else {
                    lblSupplierId.setText("S-0001");
                }

            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            btnUpdateOnAction.setDisable(true);
            for (SupplierTm tm : tmList) {
                if (tm.getSupplier_id().equals(lblSupplierId.getText())) {
                    tm.setName(txtSupplierName.getText());
                    tm.setContact(Integer.parseInt(txtSupplierContact.getText()));
                    tm.setItem(cmbItemOnAction.getValue().toString());

                    Boolean isUpdated = SupplierModel.update(tm);
                    if (isUpdated) {
                        tblSupplier.refresh();
                        new Alert(Alert.AlertType.INFORMATION, "updated..!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went Wrong..!").show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        btnUpdateOnAction.setDisable(true);
        clearFields();
    }

    public void txtSupplierContact(ActionEvent actionEvent) {

    }

    public void txtSupplierName(ActionEvent actionEvent) {

    }

    public void cmbItemOnAction(ActionEvent actionEvent) {

    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (txtSupplierContact.getText().matches("^[0-9]*$")) {
        SupplierTm supplierTm = new SupplierTm(
                lblSupplierId.getText(),
                txtSupplierName.getText(),
                Integer.parseInt(txtSupplierContact.getText()),
                cmbItemOnAction.getValue().toString()
        );
        try {
            Boolean isSaved = SupplierModel.save(supplierTm);
            if (isSaved) {
                clearFields();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid contact No").show();
        }
    }

    private void clearFields() {
        idGen();
        txtSupplierName.clear();
        txtSupplierContact.clear();
        cmbItemOnAction.setValue("");
        tblSupplier.refresh();
        loadAllSuppliers();
    }

}