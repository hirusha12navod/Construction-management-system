package lk.ijse.construction.dao.custom.impl;


import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.SupNameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierNameDaoImpl {
    public static List<SupNameDto> getList(String sup) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT supplier_name FROM supplier WHERE supplyItems=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,sup);

        List<SupNameDto> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new SupNameDto(
                    resultSet.getString(1)
            ));
        }
        return data;
    }
}
