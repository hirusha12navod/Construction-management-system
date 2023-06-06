package lk.ijse.construction.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class ItemNameTakeModel {
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
    }

    public static ItemCall searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT item_Id FROM item WHERE item_name= ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new ItemCall(
                    resultSet.getString(1)
            );
        }
        return new ItemCall();
    }
}
