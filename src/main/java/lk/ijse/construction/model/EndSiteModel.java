package lk.ijse.construction.model;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.EndSite;
import lk.ijse.construction.dto.Site;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EndSiteModel {
    String SerialId = "";



    public static List<EndSite> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT site_name,start_date FROM site";

        List<EndSite> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new EndSite(
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

    public static EndSite searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT site_name,start_date FROM site WHERE site_name = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new EndSite(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }
}
