package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.EndSiteDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndSiteDaoImpl {
    String SerialId = "";



    public static List<EndSiteDto> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT site_name,start_date FROM site";

        List<EndSiteDto> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new EndSiteDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return data;
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM site");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(2));
        }
        return data;
    }

    public static EndSiteDto searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT site_name,start_date FROM site WHERE site_name = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new EndSiteDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }
}
