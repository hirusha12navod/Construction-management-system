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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.bo.BoFactory;
import lk.ijse.construction.bo.custom.HardwareCustomerBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.HardwareCustomerDto;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class HardwareCustomerRegController {

    public AnchorPane customerRegistrationPane;
    public JFXButton back;

    String SerialId="";



    public TextField txtConNo;
    public JFXTextField txtCName;
    public Label lblCustomerId;
    public JFXButton btnCUpdate;
    public JFXButton btnCClear;
    public JFXButton btnSaveOnAction;
    public JFXButton btnIdGenerateOnAction;
    public JFXButton btnSearchOnAction;
    public JFXComboBox cmbCId;

//    HardwareCustomerDao hardwareCustomerDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.HARDWARE_CUSTOMER_DAO);
    HardwareCustomerBo hardwareCustomerBo = BoFactory.getInstance().getBo(BoFactory.BoType.HARDWARE_CUSTOMER_BO);
    @FXML
    void initialize() {
        idGen();
        cmbCId.setVisible(false);
        btnCUpdate.setVisible(false);

        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) customerRegistrationPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void loadCustomerIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = hardwareCustomerBo.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbCId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent actionEvent){
        String id=String.valueOf(cmbCId.getValue());

        try {
            HardwareCustomerDto customer = hardwareCustomerBo.searchById(id);
            txtCName.setText(customer.getName());
            lblCustomerId.setText(customer.getCustomer_Id());
            txtConNo.setText(customer.getContact());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    public void idGen(){
        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queMax="SELECT customer_Id FROM customer ORDER BY customer_Id DESC LIMIT 1";
            PreparedStatement preparedStatement = con.prepareStatement(queMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1).split("-")[1];
                int num = Integer.parseInt(id);
                lblCustomerId.setText(String.format("C-%04d", ++num));
            }else {
                lblCustomerId.setText("C-0001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        cmbCId.setVisible(true);
        loadCustomerIds();
        lblCustomerId.setVisible(false);
        btnSaveOnAction.setVisible(false);
        btnCUpdate.setVisible(true);
    }



    public void btnSaveOnAction(ActionEvent actionEvent){
        if (txtConNo.getText().matches("^\\+[0-9]{10,12}$") || txtConNo.getText().matches("^[0-9]{10,10}$")) {
            String id=lblCustomerId.getText();
            String name=txtCName.getText();
            String cont=txtConNo.getText();

        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queSave= "INSERT INTO customer(customer_id,name,contact) VALUES(?,?,?)";
            PreparedStatement stSave=con.prepareStatement(queSave);
            stSave.setString(1,id);
            stSave.setString(2,name);
            stSave.setString(3,cont);
            stSave.executeUpdate();
            //con.close();

            new Alert(Alert.AlertType.INFORMATION,"Customer ID:"+id+"Saved Succcessful!").show();
            makanna();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid contact No").show();
        }
    }
    public void makanna() {
        txtConNo.setText("");
        txtCName.setText("");
        lblCustomerId.setText("");
        cmbCId.setVisible(false);
        lblCustomerId.setVisible(true);
        btnSaveOnAction.setVisible(true);
        btnCUpdate.setVisible(false);
        idGen();
    }
    public void btnUpdateOnAction(ActionEvent actionEvent){
        if (txtConNo.getText().matches("^\\+[0-9]{10,12}$") || txtConNo.getText().matches("^[0-9]{10,10}$")) {
            String id=String.valueOf(cmbCId.getValue());
            String name=txtCName.getText();
            String cont= txtConNo.getText();

        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queUpdate="UPDATE customer SET name=?,contact=? WHERE customer_Id=?";
            PreparedStatement stUp=con.prepareStatement(queUpdate);
            stUp.setString(3,id);
            stUp.setString(1,name);
            stUp.setString(2,cont);
            stUp.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION,"Customer ID:"+id+" UPDATED Successfully!").show();
            makanna();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid contact No").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent){
        makanna();

    }
}
