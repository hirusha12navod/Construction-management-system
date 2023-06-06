package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;

import java.io.IOException;
import java.sql.*;

public class LoggingController {
    public JFXButton btnLogging;
    public JFXButton btnCClear;
    public TextField txtUserName;
    public TextField txtPassword;
    public Pane LoggingPane;
    public JFXButton btnLogging1;
    public Pane RegisterPane;
    public TextField txtNewUserName;
    public TextField txtNewPassword;
    public TextField txtReEnterPassword;
    public JFXButton btnRegister;
    public JFXButton btnCClear1;


    public Label lblUn;
    public Label lblPw;
    public Label lblBis;
    public Pane ConfirmPane;
    public JFXButton btnCClear11;
    public TextField txtOldUserName1;
    public TextField txtOldPassword1;
    public JFXButton btnOkOnAction1;

    @FXML
    void initialize(){
        ConfirmPane.setVisible(false);
        RegisterPane.setVisible(false);
        lblUn.setVisible(true);
        lblBis.setVisible(true);
        lblPw.setVisible(true);
        btnCClear.setVisible(true);
        btnLogging.setVisible(true);
        btnLogging1.setVisible(true);
    }

    public void btnLoggingOnAction(ActionEvent actionEvent) {
        String un=txtUserName.getText();
        String pw=txtPassword.getText();

        //new Alert(Alert.AlertType.WARNING, un+pw+rpw).show();

        if(un.isEmpty()||pw.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "All the Fields should be Filled!").show();
        }
        else{
            try {
                Connection con = DBconnection.getInstance().getConnection();
                String queLog="SELECT * FROM users WHERE username='"+un+"' AND password='"+pw+"'";
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(queLog);

                if(rs.next()){
                            Stage stage = (Stage) LoggingPane.getScene().getWindow();
                            try {
                                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                                stage.centerOnScreen();
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                else{
                    new Alert(Alert.AlertType.WARNING, "Incorrect Username or Password!").show();
                    txtUserName.setText("");
                    txtPassword.setText("");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        makanna();
    }

    public void btnClear1OnAction(ActionEvent actionEvent) {
        makanna2();
        makanna3();
        ConfirmPane.setVisible(false);
        RegisterPane.setVisible(false);
        lblUn.setVisible(true);
        lblBis.setVisible(true);
        lblPw.setVisible(true);
        btnCClear.setVisible(true);
        btnLogging.setVisible(true);
        btnLogging1.setVisible(true);
        txtUserName.setVisible(true);
        txtPassword.setVisible(true);
    }

    public void btnLogging1OnAction(ActionEvent actionEvent) {
        ConfirmPane.setVisible(true);
        RegisterPane.setVisible(false);
        lblUn.setVisible(false);
        lblBis.setVisible(false);
        lblPw.setVisible(false);
        btnCClear.setVisible(false);
        btnLogging.setVisible(false);
        btnLogging1.setVisible(false);
        txtUserName.setVisible(false);
        txtPassword.setVisible(false);
        txtUserName.setText("");
        txtPassword.setText("");
    }

    public void btnRegOnAction(ActionEvent actionEvent) {
        String un=txtNewUserName.getText();
        String pw=txtNewPassword.getText();
        String rpw=txtReEnterPassword.getText();

        //new Alert(Alert.AlertType.WARNING, un+pw+rpw).show();

        if(un.isEmpty()||pw.isEmpty()||rpw.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "All the Fields should be Filled!").show();
        }
        else if(!pw.equals(rpw)){
            new Alert(Alert.AlertType.WARNING, "Password & Retype Password is not match!").show();
        }
        else if(pw.length()<8){
            new Alert(Alert.AlertType.WARNING, "Your Password is Not Strong Engough!"+"/n"+"Please Enter at least 8 characters for the Password Field!").show();
        }
        else{
            try {
                Connection con = DBconnection.getInstance().getConnection();
                String queSave = "INSERT INTO users(username,password) VALUES(?,?)";
                PreparedStatement stSave = con.prepareStatement(queSave);
                stSave.setString(1, un);
                stSave.setString(2, pw);
                stSave.executeUpdate();
                con.close();

                new Alert(Alert.AlertType.INFORMATION, "Employee ID:" + un + " Saved Successfully!").show();
                //makanna();

                RegisterPane.setVisible(false);
                lblUn.setVisible(true);
                lblBis.setVisible(true);
                lblPw.setVisible(true);
                btnCClear.setVisible(true);
                btnLogging.setVisible(true);
                btnLogging1.setVisible(true);
                txtUserName.setVisible(true);
                txtPassword.setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void makanna(){
        txtUserName.setText("");
        txtPassword.setText("");
    }
    public void makanna2(){
        txtNewUserName.setText("");
        txtNewPassword.setText("");
        txtReEnterPassword.setText("");
    }
    public void makanna3(){
        txtOldUserName1.setText("");
        txtOldPassword1.setText("");
    }

    public void btnOkOnAction(ActionEvent actionEvent) {
        String oun=txtOldUserName1.getText();
        String opw=txtOldPassword1.getText();

        //new Alert(Alert.AlertType.WARNING, un+pw+rpw).show();

        if(oun.isEmpty()||opw.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "All the Fields should be Filled!").show();
        }
        else{
            try {
                Connection con = DBconnection.getInstance().getConnection();
                String queLog="SELECT * FROM users WHERE username='"+oun+"' AND password='"+opw+"'";
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(queLog);

                if(rs.next()){
                    ConfirmPane.setVisible(false);
                    RegisterPane.setVisible(true);
                    lblUn.setVisible(false);
                    lblBis.setVisible(false);
                    lblPw.setVisible(false);
                    btnCClear.setVisible(false);
                    btnLogging.setVisible(false);
                    btnLogging1.setVisible(false);
                    txtUserName.setVisible(false);
                    txtPassword.setVisible(false);
                    txtUserName.setText("");
                    txtPassword.setText("");
                }
                else{
                    new Alert(Alert.AlertType.WARNING, "Incorrect Username or Password!").show();
                    txtOldUserName1.setText("");
                    txtOldPassword1.setText("");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

