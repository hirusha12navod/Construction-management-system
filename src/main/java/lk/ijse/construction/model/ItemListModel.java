package lk.ijse.construction.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.ItemCategory;
import lk.ijse.construction.dto.ItemL;
import lk.ijse.construction.dto.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemListModel {




    public static List<ItemL> getList(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT item_name FROM item WHERE item_category=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,id);

        List<ItemL> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new ItemL(
                    resultSet.getString(1)
            ));
        }
        return data;
        /*Connection con = DriverManager.getConnection(URL, props);
        String sql = "SELECT Item_name FROM item WHERE item_category='id'";

        List<ItemL> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new ItemL(
                    resultSet.getString(1)
            ));
        }
        return data;*/
    }

    public static List<Items> getAll(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT item_Id,item_name,initial_stock,price,rack_no FROM item WHERE item_category=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,id);

        List<Items> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new Items(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
                                ));
        }
        return data;
    }

    public static double getPrice(String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT price FROM item WHERE item_name=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,name);

        ResultSet resultSet = lstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0;
    }

    public static int getQty(String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT initial_stock FROM item WHERE item_name=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,name);

        ResultSet resultSet = lstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static boolean updateStock(int qty,String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "UPDATE item SET initial_stock=? WHERE item_name=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setInt(1,qty);
        lstm.setString(2,name);

        return lstm.executeUpdate()>0;
    }

    public static String getRack(String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT rack_no FROM item WHERE item_name=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,name);

        ResultSet resultSet = lstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return "";
    }

    public static ObservableList<String> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement lstm=con.prepareStatement(sql);

        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()){
            list.add(resultSet.getString(2));
        }
        return list;
    }

    /*public static List<String> loadIds() throws SQLException {
        Connection con = DriverManager.getConnection(URL,props);
        ResultSet resultSet = con.createStatement().executeQuery("SELECT item_category FROM category");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }*/

    /*public static Items getAll(String id) throws SQLException {
        Connection con = DriverManager.getConnection(URL,props);

        PreparedStatement pstm = con.prepareStatement("SELECT item_Id,item_name,initial_stock,price,rack_no FROM item WHERE item_category = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Items(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }*/
}
