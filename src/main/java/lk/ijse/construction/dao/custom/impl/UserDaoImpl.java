package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl {

    String SerialId = "";

    public static List<UserDto> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM users";

        List<UserDto> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new UserDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return data;
    }
}
