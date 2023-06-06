package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.BillDao;
import lk.ijse.construction.dao.custom.impl.HardwareCustomerDaoImpl;
import lk.ijse.construction.dao.custom.impl.HardwareItemAddDaoImpl;
import lk.ijse.construction.dao.custom.impl.ItemListDaoImpl;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.*;
import lk.ijse.construction.model.tm.BillTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardwareSalesBillController {
    public Label lblTotalPrice;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colItemName;
    public TableView tblBill;
    public Label lblNoOfItems;
    public JFXButton btnAdd;
    public TextField txtQty;
    public TextField txtPrice;
    public JFXComboBox cmbItem;
    public JFXComboBox cmbItemCategory;
    public JFXComboBox cmbBillCustomer;
    public Label lblBillNo;
    public JFXButton btnClear;
    public JFXButton btnBill;

    
    public AnchorPane mainPane;
    public JFXButton back;
    String SerialId="";

    BillDao billDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BILL_DAO);


    @FXML
    void initialize(){
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("amount"));
        loadCatIds();
        idGen();
        loadCusIds();
        back.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/DashboardForm2.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //cmbEmployeeId.setVisible(false);
        //btnupdate.setVisible(false);
    }

    private void loadCusIds() {
        try {
            List<String> ids = HardwareCustomerDaoImpl.loadIds();
            ObservableList<String> names = FXCollections.observableArrayList();
            for (String id : ids) {
                names.add(HardwareCustomerDaoImpl.getName(id));
            }
            cmbBillCustomer.setItems(names);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void idGen() {
        try{
            Connection con= DBconnection.getInstance().getConnection();
            String queMax="SELECT billNum FROM salesbill ORDER BY billNum DESC LIMIT 1";
            PreparedStatement preparedStatement = con.prepareStatement(queMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString(1).split("-")[1];
                int num = Integer.parseInt(id);
                lblBillNo.setText(String.format("EE-%07d", ++num));
            }else {
                lblBillNo.setText("EE-0000001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCatIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = HardwareItemAddDaoImpl.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbItemCategory.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    ObservableList<BillTm> tmList = FXCollections.observableArrayList();
    public void btnAddOnAction(ActionEvent actionEvent) {
        tmList.add(new BillTm(
                cmbItem.getValue().toString(),
                Integer.parseInt(txtQty.getText()),
                Integer.parseInt(txtQty.getText())*Double.parseDouble(txtPrice.getText())
        ));
        System.out.println(tmList);
        tblBill.setItems(tmList);

        lblNoOfItems.setText(String.valueOf(tmList.size()));
        double total = 0;
        for (BillTm tm:tmList) {
            total += tm.getAmount();
        }
        lblTotalPrice.setText(String.valueOf(total));

        cmbItemCategory.setValue("");
        txtQty.clear();
        txtPrice.clear();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnBillOnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = null;
        try {
            connection = DBconnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            Boolean alert = false;
            for (int i = 0; i < tmList.size(); i++) {
                Boolean isUpdated = ItemListDaoImpl.updateStock(ItemListDaoImpl.getQty(tmList.get(i).getName()) - tmList.get(i).getQty(), tmList.get(i).getName());
                if (!isUpdated){
                    alert = true;
                }
            }
            if (alert){
                new Alert(Alert.AlertType.ERROR,"Something went Wrong..!").show();
            }else{
                List<SalesDetailsDto> list = new ArrayList<>();
                for(BillTm tm:tmList) {

                    list.add(new SalesDetailsDto(
                            lblBillNo.getText(),
                            tm.getName(),
                            tm.getQty(),
                            tm.getAmount()
                    ));
                }
                Boolean isOrderPlaced = billDao.save(new SalesDto(
                        lblBillNo.getText(),
                        cmbBillCustomer.getValue().toString(),
                        Double.parseDouble(lblTotalPrice.getText()),
                        list
                        ));
                if (isOrderPlaced) {
                    Boolean detailSaved = billDao.saveDetails(
                            new SalesDto(
                                    lblBillNo.getText(),
                                    cmbBillCustomer.getValue().toString(),
                                    Double.parseDouble(lblTotalPrice.getText()),
                                    list
                            )
                    );
                    if (detailSaved) {
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Billed..!").show();
                        print();
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            connection.rollback();
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
        lblTotalPrice.setText(null);
        lblNoOfItems.setText(null);
        idGen();
    }

    private void print() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("D:\\Example2\\Example\\src\\main\\resources\\jasper\\Report.jrxml");
            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT salesBill.billNum,salesBill.customer,salesBill.totalPrice,salesBillDetails.itemName,salesBillDetails.qty,salesBillDetails.unitPrice FROM salesBill INNER JOIN salesBillDetails ON salesBill.billNum=salesBillDetails.billNumber WHERE salesBill.billNum='"+lblBillNo.getText()+"'");
            jasperDesign.setQuery(query);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBconnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
            tblBill.getItems().clear();
            cmbBillCustomer.setValue("");
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void cmbItemCategoryOnAction(ActionEvent actionEvent) {
        String id=String.valueOf(cmbItemCategory.getValue().toString()!=null? cmbItemCategory.getValue().toString():"");

        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<ItemLDto> ids = ItemListDaoImpl.getList(id);

            for (ItemLDto idm : ids) {
                obList.add(idm.getItem_name());
            }
            cmbItem.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        if (cmbItem.getValue()!=null) {
            try {
                txtPrice.setText(String.valueOf(ItemListDaoImpl.getPrice(cmbItem.getValue().toString() != null ? cmbItem.getValue().toString() : "")));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
