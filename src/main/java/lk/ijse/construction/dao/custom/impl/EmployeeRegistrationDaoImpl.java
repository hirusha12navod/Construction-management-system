package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.EmployeeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRegistrationDaoImpl {

    String SerialId = "";


    public static List<EmployeeDto> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Employee";

        List<EmployeeDto> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return data;
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT emp_Id FROM employee");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static EmployeeDto searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM employee WHERE emp_Id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }
}
