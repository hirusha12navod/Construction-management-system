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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.bo.BoFactory;
import lk.ijse.construction.bo.custom.EmployeeBo;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.EmployeeDto;

import java.io.IOException;
import java.sql.*;
import java.util.List;


public class EmployeeRegistrationController{
    public AnchorPane EmployeeRegistrationPane;
    public JFXButton back;

    String SerialId="";

    public JFXTextField txtfname;
    public JFXTextField txtmname;
    public JFXTextField txtlname;
    public JFXTextField txtcontactno;
    public JFXTextField txtnic;
    public JFXTextField txtlane;
    public JFXTextField txtcity;
    public JFXTextField txtdistrict;
    public JFXButton btnupdate;
    public JFXButton btnclear;
    public JFXTextField txtempid;
    public JFXTextField txtdesignation;
    public JFXButton btnIdGenerateOnAction;
    public Label lblEmployeeId;
    public JFXButton btnSearchOnAction;
    public JFXComboBox cmbEmployeeId;
    public JFXButton btnSave;

//    EmployeeRegistrationDao employeeRegistrationDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE_REGISTRATION_DAO);
    EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoFactory.BoType.EMPLOYEE_BO);

    @FXML
    void initialize(){
        idGen();
        cmbEmployeeId.setVisible(false);
        btnupdate.setVisible(false);

        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) EmployeeRegistrationPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    private void loadEmpIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = employeeBo.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbEmployeeId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent actionEvent){
        String id=String.valueOf(cmbEmployeeId.getValue());

        try {
            EmployeeDto employee = employeeBo.searchById(id);
            txtfname.setText(employee.getEmpName());
            txtdesignation.setText(employee.getDesignation());
            txtcontactno.setText(employee.getContact_no());
            txtnic.setText(employee.getNic());
            txtlane.setText(employee.getEAddress());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    public void idGen(){
        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queMax="SELECT emp_id FROM employee ORDER BY emp_Id DESC LIMIT 1";
            PreparedStatement preparedStatement = con.prepareStatement(queMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1).split("-")[1];
                int num = Integer.parseInt(id);
                lblEmployeeId.setText(String.format("E-%04d", ++num));
            }else {
                lblEmployeeId.setText("E-0001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        cmbEmployeeId.setVisible(true);
        loadEmpIds();
        lblEmployeeId.setVisible(false);
        btnSave.setVisible(false);
        btnupdate.setVisible(true);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (txtcontactno.getText().matches("^[0-9]{10,10}$")) {
            if (txtnic.getText().matches("^\\d{12}$") || txtnic.getText().matches("^\\d{9}[Vv]$") || txtnic.getText().matches("^\\d{9}[Xx]$")) {
            String id = lblEmployeeId.getText();
            String name = txtfname.getText();
            String desi = txtdesignation.getText();
            String cont = txtcontactno.getText();
            String status = "Active";
            String add = txtlane.getText();
            String nic = txtnic.getText();

            try {
                Connection con = DBconnection.getInstance().getConnection();
                String queSave = "INSERT INTO employee(emp_id,EmpName,designation,contact_no,status,EAddress,nic) VALUES(?,?,?,?,?,?,?)";
                PreparedStatement stSave = con.prepareStatement(queSave);
                stSave.setString(1, id);
                stSave.setString(2, name);
                stSave.setString(3, desi);
                stSave.setString(4, cont);
                stSave.setString(5, status);
                stSave.setString(6, add);
                stSave.setString(7, nic);
                stSave.executeUpdate();

                new Alert(Alert.AlertType.INFORMATION, "Employee ID:" + id + " Saved Successfully!").show();
                makanna();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid contact No").show();
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC No").show();
        }
    }


    public void makanna(){
        lblEmployeeId.setText("");
        txtfname.setText("First Name");
        txtdesignation.setText("");
        txtcontactno.setText("");
        txtlane.setText("");
        txtnic.setText("");
        cmbEmployeeId.setVisible(false);
        lblEmployeeId.setVisible(true);
        btnSave.setVisible(true);
        btnupdate.setVisible(false);
        idGen();
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        if (txtcontactno.getText().matches("^\\+[0-9]{10,12}$") || txtcontactno.getText().matches("^[0-9]{10,10}$")) {
            if (txtnic.getText().matches("^\\d{12}$") || txtnic.getText().matches("^\\d{9}[Vv]$") || txtnic.getText().matches("^\\d{9}[Xx]$")) {
                String id=String.valueOf(cmbEmployeeId.getValue());
                String name=txtfname.getText();
                String desi=txtdesignation.getText();
                String cont=txtcontactno.getText();
                String add=txtlane.getText();
                String nic=txtnic.getText();

            try{
                Connection con= DBconnection.getInstance().getConnection();
                String queUpdate="UPDATE employee SET designation=?,contact_no=?,nic=?,EmpName=?,EAddress=? WHERE emp_Id=?";
                PreparedStatement stUp=con.prepareStatement(queUpdate);
                stUp.setString(6,id);
                stUp.setString(4,name);
                stUp.setString(1,desi);
                stUp.setString(2,cont);
                stUp.setString(5,add);
                stUp.setString(3,nic);
                stUp.executeUpdate();

                new Alert(Alert.AlertType.INFORMATION,"Employee ID:"+id+" UPDATED Successfully!").show();
                makanna();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid NIC No").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid contact No").show();
        }
    }

    public void btnclearOnAction(ActionEvent actionEvent) {
        makanna();
    }
}
