package lk.ijse.construction.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.ItemCategory;
import lk.ijse.construction.dto.MaterialTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConstructionModel {

    String SerialId = "";

    public static double getStock(String material) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT stock FROM constructionst WHERE material_name = ?");
        pstm.setString(1,material);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  resultSet.getDouble(1);
        }
        return 0;
    }

    public static void updateStock(double stock,String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("UPDATE constructionst SET stock=? WHERE material_name=?");
        pstm.setDouble(1,stock);
        pstm.setString(2,name);
        pstm.executeUpdate();
    }

    public static ObservableList<MaterialTm> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM constructionst");
        ObservableList<MaterialTm> list = FXCollections.observableArrayList();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new MaterialTm(
                    resultSet.getString(1),
                    resultSet.getDouble(2)
            ));
        }
        return list;
    }
}
