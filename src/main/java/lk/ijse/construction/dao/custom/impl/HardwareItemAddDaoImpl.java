package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.ItemCategoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardwareItemAddDaoImpl {

    String SerialId = "";



    public static List<ItemCategoryDto> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM category";

        List<ItemCategoryDto> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new ItemCategoryDto(
                    resultSet.getString(1)
            ));
        }
        return data;
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT item_category FROM category");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static ItemCategoryDto searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM category WHERE item_category = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new ItemCategoryDto(
                    resultSet.getString(1)
            );
        }
        return null;
    }
}
