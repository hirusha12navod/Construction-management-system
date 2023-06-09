package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.HardwareItemAddDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardwareItemAddDaoImpl implements HardwareItemAddDao {

    String SerialId = "";


    @Override
    public List<Category> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM category";

        List<Category> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Category(
                    resultSet.getString(1)
            ));
        }
        return data;
    }

    @Override
    public Category get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Category dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Category dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Category itemCategoryDto) throws SQLException, ClassNotFoundException {
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
    @Override
    public List<String> loadIds() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT item_category FROM category");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
    @Override
    public Category searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM category WHERE item_category = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Category(
                    resultSet.getString(1)
            );
        }
        return null;
    }
}
