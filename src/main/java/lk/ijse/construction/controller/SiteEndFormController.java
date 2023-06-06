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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.EndSite;
import lk.ijse.construction.dto.Site;
import lk.ijse.construction.model.EndSiteModel;
import lk.ijse.construction.model.SiteModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;

public class SiteEndFormController {
    public AnchorPane finishSitePane;
    public JFXButton back;
    public Label lblSiteStartDate;
    public JFXButton btnSaveOnAction;
    public JFXButton btnClearOnAction;
    public Label lblSiteEndDate;
    public Button btnSiteEndOnAction;
    public Label lblDate;
    public Button btnSiteCalculateOnAction;
    public JFXComboBox cmbSiteName;



    @FXML
    void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) finishSitePane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try{
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = EndSiteModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbSiteName.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void makanna(){
        lblSiteEndDate.setText("");
        lblDate.setText("");
        lblSiteStartDate.setText("");
        cmbSiteName.setValue("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id=String.valueOf(cmbSiteName.getValue());
        String ed=lblSiteEndDate.getText();
        String nd=lblDate.getText();

        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queUpdate="UPDATE site SET end_date=?,no_of_days=? WHERE site_name=?";
            PreparedStatement stUp=con.prepareStatement(queUpdate);
            stUp.setString(3,id);
            stUp.setString(1,ed);
            stUp.setString(2,nd);
            stUp.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION,"Site :"+id+" UPDATED Successfully!").show();
            makanna();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnSiteCalculateOnAction(ActionEvent actionEvent) {
    }

    public void btnSiteEndOnAction(ActionEvent actionEvent) {
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d=LocalDate.now();
        LocalDate sd=LocalDate.parse(lblSiteStartDate.getText(),dtf);
        lblSiteEndDate.setText(String.valueOf(d));

        //lblDate.setText(String.valueOf(sd));

        long diffDays= ChronoUnit.DAYS.between(sd,d)+1;
        lblDate.setText(String.valueOf(diffDays));
    }

    public void cmbSiteNameOnAction(ActionEvent actionEvent) {
        lblSiteEndDate.setText("");
        lblDate.setText("");
        String id=String.valueOf(cmbSiteName.getValue());

        try {
            EndSite Esite = EndSiteModel.searchById(id);
            if (Esite!=null) {
                lblSiteStartDate.setText(Esite.getStart_date());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        makanna();
    }
}
