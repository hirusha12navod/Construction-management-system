package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.UserDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    String SerialId = "";

    @Override
    public List<UserDto> getAll() throws SQLException {
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

    @Override
    public UserDto get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(UserDto userDto) throws SQLException, ClassNotFoundException {
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
}
