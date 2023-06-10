package lk.ijse.construction.dao.custom.impl;
import lk.ijse.construction.dao.custom.ItemListDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.Item;
import lk.ijse.construction.model.ItemLDto;
import lk.ijse.construction.model.ItemsDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemListDaoImpl implements ItemListDao {

    @Override
    public List<Item> getList(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT item_name FROM item WHERE item_category=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,id);

        List<Item> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new Item(
                    "", resultSet.getString(1),
                    "",0,0,""
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

    @Override
    public List<Item> getAll(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE item_category=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,id);

        List<Item> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
                                ));
        }
        return data;
    }

    @Override
    public double getPrice(String name) throws SQLException {
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

    @Override
    public int getQty(String name) throws SQLException {
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

    @Override
    public boolean updateStock(int qty,String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "UPDATE item SET initial_stock=? WHERE item_name=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setInt(1,qty);
        lstm.setString(2,name);

        return lstm.executeUpdate()>0;
    }

    @Override
    public String getRack(String name) throws SQLException {
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

    @Override
    public List<Item> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement lstm=con.prepareStatement(sql);

        List<Item> list = new ArrayList<>(); //2
        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()){
            list.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            ));
        }
        return list;
    }

    @Override
    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM item WHERE item_name= ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            );
        }
        return new Item();
    }

    @Override
    public Item get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Item itemsDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        return null;
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
