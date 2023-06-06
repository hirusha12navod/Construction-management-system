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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.SiteDto;
import lk.ijse.construction.dao.custom.impl.SiteDaoImpl;

import java.time.LocalDate;

import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SiteRegistrationController {


    public Label lblEmpId;
    public JFXComboBox cmbEmpId;
    public JFXTextField txtSiteName;
    public JFXTextField txtConact;
    public JFXTextField txtContactNo;
    public JFXTextField txtLane;
    public JFXButton btnSaveOnAction;
    public JFXButton btnUpdateOnAction;
    public JFXButton btnClearOnAction;
    public AnchorPane siteRegistrationPane;
    public JFXButton back;
    public Label lblSiteId;
    public JFXComboBox cmbSearchId;
    public JFXComboBox cmbDate;
    public JFXButton btnSearchOnAction;
    public DatePicker dtpDate;
    String SerialId="";

    @FXML
    void initialize() {
        idGen();
        cmbSearchId.setVisible(false);
        btnUpdateOnAction.setVisible(false);
        dtpDate.setValue(LocalDate.now());
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) siteRegistrationPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void idGen(){
        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queMax="SELECT site_Id FROM site ORDER BY site_Id DESC LIMIT 1";
            PreparedStatement preparedStatement = con.prepareStatement(queMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1).split("-")[1];
                int num = Integer.parseInt(id);
                lblSiteId.setText(String.format("S-%04d", ++num));
            }else {
                lblSiteId.setText("S-0001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        makanna();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=String.valueOf(cmbSearchId.getValue());
        String name=txtSiteName.getText();
        String loc=txtLane.getText();
        String conP=txtConact.getText();
        LocalDate sDate=dtpDate.getValue();
        String conNo=txtContactNo.getText();

        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queUpdate="UPDATE site SET site_name=?,location=?,contact_person=?,start_date=?,contactNo=? WHERE site_Id=?";
            PreparedStatement stUp=con.prepareStatement(queUpdate);
            stUp.setString(6,id);
            stUp.setString(1,name);
            stUp.setString(2,loc);
            stUp.setString(3,conP);
            stUp.setString(4,String.valueOf(sDate));
            stUp.setString(5,conNo);
            stUp.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION,"Employee ID:"+id+" UPDATED Successfully!").show();
            makanna();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (txtConact.getText().matches("^\\+[0-9]{10,12}$") || txtConact.getText().matches("^[0-9]{10,10}$"))  {
            String id = lblSiteId.getText();
            String name = txtSiteName.getText();
            String loc = txtLane.getText();
            String cont = txtConact.getText();
            String conNo=txtContactNo.getText();
            LocalDate date=dtpDate.getValue();
            String stat="Active";

        if(name.isEmpty() || loc.isEmpty() || cont.isEmpty() || cont.isEmpty() || conNo.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Fill the Required Information for the Form!").show();
        }
        else{
            try {
                Connection con = DBconnection.getInstance().getConnection();
                String queSave = "INSERT INTO site(site_Id,site_name,location,contact_person,contactNo,start_date) VALUES(?,?,?,?,?,?)";
                PreparedStatement stSave = con.prepareStatement(queSave);
                stSave.setString(1, id);
                stSave.setString(2, name);
                stSave.setString(3, loc);
                stSave.setString(4, cont);
                stSave.setString(5, conNo);
                stSave.setString(6, String.valueOf(date));
                stSave.executeUpdate();

                new Alert(Alert.AlertType.INFORMATION, "Employee ID:" + id + " Saved Successfully!").show();
                makanna();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid contact No").show();
        }
    }

    private void makanna(){
        lblSiteId.setText("");
        txtSiteName.setText("");
        txtConact.setText("");
        txtLane.setText("");
        txtContactNo.setText("");
        cmbSearchId.setVisible(false);
        lblSiteId.setVisible(true);
        btnUpdateOnAction.setVisible(false);
        btnSaveOnAction.setVisible(true);
        dtpDate.setValue(LocalDate.now());
        idGen();
    }

    public void dtpDateOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        cmbSearchId.setVisible(true);
        loadSiteIds();
        lblSiteId.setVisible(false);
        btnSaveOnAction.setVisible(false);
        btnUpdateOnAction.setVisible(true);
    }

    public void loadSiteIds(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = SiteDaoImpl.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbSearchId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void cmbSearchIdOnActon(ActionEvent actionEvent) {
        String id=String.valueOf(cmbSearchId.getValue());

        try {
            SiteDto site = SiteDaoImpl.searchById(id);
            txtSiteName.setText(site.getSite_name());
            txtLane.setText(site.getLocation());
            txtConact.setText(site.getContact_person());
            txtContactNo.setText(site.getContactNo());
            String sdate=site.getStart_date();

            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dt=LocalDate.parse(sdate,dtf);
            dtpDate.setValue(dt);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
}
