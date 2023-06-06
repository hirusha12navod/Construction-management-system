package lk.ijse.construction.model;


import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.ItemL;
import lk.ijse.construction.dto.SupName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierNameModel {
    public static List<SupName> getList(String sup) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT supplier_name FROM supplier WHERE supplyItems=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,sup);

        List<SupName> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new SupName(
                    resultSet.getString(1)
            ));
        }
        return data;
    }
}
