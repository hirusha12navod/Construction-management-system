package lk.ijse.construction.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.ItemL;
import lk.ijse.construction.dto.SupplierTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SupplierModel {


    public static ObservableList<SupplierTm> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier";
        PreparedStatement lstm=con.prepareStatement(sql);

        ObservableList<SupplierTm> data = FXCollections.observableArrayList();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new SupplierTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    public static Boolean save(SupplierTm tm) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "INSERT INTO supplier VALUES(?,?,?,?) ";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1, tm.getSupplier_id());
        lstm.setString(2, tm.getName());
        lstm.setInt(3, tm.getContact());
        lstm.setString(4, tm.getItem());

        return lstm.executeUpdate()>0;

    }

    public static Boolean update(SupplierTm tm) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "UPDATE supplier SET supplier_name = ?,contact=?,supplyItems=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1, tm.getName());
        lstm.setString(2, tm.getSupplier_id());
        lstm.setInt(3, tm.getContact());
        lstm.setString(4, tm.getItem());

        return lstm.executeUpdate()>0;
    }
}
