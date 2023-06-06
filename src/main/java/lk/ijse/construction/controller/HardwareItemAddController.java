package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.HardwareItemAddModel;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class HardwareItemAddController extends Component {

    public AnchorPane addItemsPane;
    public JFXButton back;
    String SerialId="";


    public JFXTextField txtItemsCategory;
    public JFXButton btnSaveOnAction;
    public Label lblItemsId;
    public JFXTextField txtItemName;
    public TextField txtItemPrice;
    public TextField txtRackNo;
    public TextField txtItemStock;
    public JFXButton btnClearOnAction;
    public JFXButton btnIdGenerateOnAction;
    public JFXComboBox cmbItemCategory;
    public JFXButton btnBackOnAction;

    @FXML
    private AnchorPane root;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

    }

    private void loadItemCatogories() {
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

    @FXML
    void initialize(){
        loadItemCatogories();
        ItemIdGen();

        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) addItemsPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void ItemIdGen(){
        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queMax="SELECT item_Id FROM item ORDER BY item_Id DESC LIMIT 1";
            PreparedStatement preparedStatement = con.prepareStatement(queMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1).split("-")[1];
                int num = Integer.parseInt(id);
                lblItemsId.setText(String.format("I-%04d", ++num));
            }else {
                lblItemsId.setText("I-0001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String cato = String.valueOf(cmbItemCategory.getValue());
        String itemId = lblItemsId.getText();
        String name = txtItemName.getText();
        String price = txtItemPrice.getText();
        String rack = txtRackNo.getText();
        String stock = txtItemStock.getText();

        try {
            Connection con = DBconnection.getInstance().getConnection();
            String queSave = "INSERT INTO item(item_category,item_id,item_name,price,rack_no,initial_stock) VALUES(?,?,?,?,?,?)";
            PreparedStatement stSave = con.prepareStatement(queSave);
            stSave.setString(1, cato);
            stSave.setString(2, itemId);
            stSave.setString(3, name);
            stSave.setString(4, price);
            stSave.setString(5, rack);
            stSave.setString(6, stock);
            stSave.execute();

            new Alert(Alert.AlertType.INFORMATION, "item_id : " + itemId + " Saved Successful! ").show();
            makanna();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void makanna(){
        lblItemsId.setText("");
        txtItemName.setText("");
        txtItemPrice.setText("");
        txtItemStock.setText("");
        cmbItemCategory.setValue("");
        txtRackNo.setText("");
        ItemIdGen();
    }
    public void btnClearOnAction(ActionEvent actionEvent){
        makanna();
        }
    }
