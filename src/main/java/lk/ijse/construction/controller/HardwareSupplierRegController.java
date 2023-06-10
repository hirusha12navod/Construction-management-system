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
import lk.ijse.construction.bo.BoFactory;
import lk.ijse.construction.bo.custom.ItemListBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.SupplierDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.ItemsDto;
import lk.ijse.construction.model.SupplierDto;
import lk.ijse.construction.model.tm.SupplierTm;

import java.io.IOException;
import java.sql.*;

public class HardwareSupplierRegController {


    public TableView<SupplierTm> tblSupplier;
    public TableColumn clmSupplierId;
    public TableColumn clmSupplierName;
    public TableColumn clmContact;
    public TableColumn clmItem;
    public AnchorPane SupplierRegistrationPane;
    public JFXButton back;

    public Label lblSupplierId;
    public JFXButton btnUpdateOnAction;
    public JFXButton btnClearOnAction;
    public TextField txtSupplierContact;
    public JFXTextField txtSupplierName;
    public JFXComboBox cmbItemOnAction;
    public JFXButton btnSaveOnAction;

//    ItemListDao itemListDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ITEM_LIST_DAO);
    SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIER_DAO);

    ItemListBo itemListBo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM_LIST_BO);

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
            ObservableList<String> list = FXCollections.observableArrayList();
            for (ItemsDto dto:itemListBo.getAll()) {
                list.add(dto.getItem_name());
            }
            cmbItemOnAction.setItems(list);
        }catch (SQLException | ClassNotFoundException e){
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

            for (SupplierDto dto:supplierDao.getAll()) {
                tmList.add(new SupplierTm(
                        dto.getSupplier_id(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getItem()
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
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

                    Boolean isUpdated = supplierDao.update(new SupplierDto(
                            tm.getSupplier_id(),
                            tm.getName(),
                            tm.getContact(),
                            tm.getItem()
                    ));
                    if (isUpdated) {
                        tblSupplier.refresh();
                        new Alert(Alert.AlertType.INFORMATION, "updated..!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went Wrong..!").show();
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException e){
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
        try {
            Boolean isSaved = supplierDao.save(new SupplierDto(
                    lblSupplierId.getText(),
                    txtSupplierName.getText(),
                    Integer.parseInt(txtSupplierContact.getText()),
                    cmbItemOnAction.getValue().toString()
            ));
            if (isSaved) {
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
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
        tblSupplier.refresh();
    }

}
