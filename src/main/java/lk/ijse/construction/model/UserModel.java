package lk.ijse.construction.model;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.Site;
import lk.ijse.construction.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserModel {

    String SerialId = "";

    public static List<User> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM users";

        List<User> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return data;
    }
}
