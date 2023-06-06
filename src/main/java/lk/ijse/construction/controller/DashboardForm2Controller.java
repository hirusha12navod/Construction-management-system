package lk.ijse.construction.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashboardForm2Controller {

    public JFXButton btnMenu;
    public AnchorPane slider;
    public JFXButton btnHardwareManagement;
    public JFXButton btnCustReg;
    public JFXButton btnItemAdd;
    public JFXButton btnItemCat;
    public JFXButton btnItemList;
    public JFXButton btnItemSearch;
    public JFXButton btnItemUpdate;
    public JFXButton btnSalesBill;
    public JFXButton btnAddMaterial;
    public JFXButton btnMaterialList;
    public JFXButton btnConstructionManage;
    public JFXButton btnEmployeeManage;
    public JFXButton btnCalculate;
    public AnchorPane hardwarePane;
    public AnchorPane constructionPane;
    public AnchorPane employeePane;
    public JFXButton btnEmployeeReg;
    public JFXButton btnSiteEnd;
    public JFXButton btnSiteRegistration;
    public JFXButton btnSupplierReg;
    public JFXButton btnCalculateManage;
    public AnchorPane CalculatePane;
    public JFXButton btnCalculateFlow;
    public JFXButton btnCalculateSlab;
    public JFXButton btnCalculateTower;
    public JFXButton btnCalculateTiles;
    public JFXButton btnCalculateWall;
    public BorderPane dashboardPane;
    public ImageView btnLogout;
    public VBox menuPane;

    public void initialize(){
        hardwarePane.setVisible(false);
        constructionPane.setVisible(false);
        employeePane.setVisible(false);
        CalculatePane.setVisible(false);
        menuPane.setVisible(false);

        btnHardwareManagement.setOnAction(actionEvent -> {
            hardwarePane.setVisible(true);
            constructionPane.setVisible(false);
            employeePane.setVisible(false);
            CalculatePane.setVisible(false);

        });
        btnConstructionManage.setOnAction(actionEvent -> {
            hardwarePane.setVisible(false);
            constructionPane.setVisible(true);
            employeePane.setVisible(false);
            CalculatePane.setVisible(false);
        });
        btnEmployeeManage.setOnAction(actionEvent -> {
            hardwarePane.setVisible(false);
            constructionPane.setVisible(false);
            employeePane.setVisible(true);
            CalculatePane.setVisible(false);
        });
        btnCalculateManage.setOnAction(actionEvent -> {
                    hardwarePane.setVisible(false);
                    constructionPane.setVisible(false);
                    employeePane.setVisible(false);
                    CalculatePane.setVisible(true);
                });
        btnMenu.setOnAction(actionEvent -> {
            hardwarePane.setVisible(false);
            constructionPane.setVisible(false);
            employeePane.setVisible(false);
            CalculatePane.setVisible(false);
            menuPane.setVisible(true);
        });
        setActions();
    }

    private void setActions() {
        btnSalesBill.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareSalesBill.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCustReg.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareCustomerReg.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnItemAdd.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareItemAdd.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnItemCat.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareItemCategory.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnItemList.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareItemList.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnItemSearch.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareItemSearch.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnItemUpdate.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareItemUpdate.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSalesBill.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareSalesBill.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSupplierReg.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/HardwareSupplierReg.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnAddMaterial.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/ConstructionMaterialsAdd.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnMaterialList.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/ConstructionMaterialsList.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSiteEnd.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/SiteEndForm.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSiteRegistration.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/SiteRegistration.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCalculateFlow.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/CalculateFlowFlow.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCalculateSlab.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/CalculateFlowSlab.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCalculateTower.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/CalculateTower.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCalculateTiles.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/CalculateTiles.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCalculateWall.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/CalculateWall.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnEmployeeReg.setOnAction(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/EmployeeRegistration.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnLogout.setOnMouseClicked(actionEvent -> {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../../view/Logging.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void btnMenuOnAction(ActionEvent actionEvent) {

    }
}
