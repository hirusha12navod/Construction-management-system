package lk.ijse.construction.model;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.Employee;
import lk.ijse.construction.dto.HardwareCustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HardwareCustomerModel {


    String SerialId = "";



    public static List<HardwareCustomer> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";

        List<HardwareCustomer> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new HardwareCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT customer_Id FROM customer");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static HardwareCustomer searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM customer WHERE customer_Id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new HardwareCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    public static String getName(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT name FROM customer WHERE customer_Id = ?");
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
