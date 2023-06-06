package lk.ijse.construction.dao.custom.impl;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemNameTakeDaoImpl {
    public static List<ItemLDto> getList(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT item_name FROM item WHERE item_category=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,id);

        List<ItemLDto> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new ItemLDto(
                    resultSet.getString(1)
            ));
        }
        return data;
    }

    public static ItemCallDto searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT item_Id FROM item WHERE item_name= ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new ItemCallDto(
                    resultSet.getString(1)
            );
        }
        return new ItemCallDto();
    }
}
