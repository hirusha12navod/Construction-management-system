package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.EndSiteDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.EndSite;
import lk.ijse.construction.model.EndSiteDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndSiteDaoImpl implements EndSiteDao {
    String SerialId = "";


    @Override
    public List<EndSite> getAll() throws SQLException {
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

    @Override
    public EndSite get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(EndSite dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EndSite dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(EndSite endSiteDto) throws SQLException, ClassNotFoundException {
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
    @Override
    public List<String> loadIds() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT * FROM site");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(2));
        }
        return data;
    }
    @Override
    public EndSite searchById(String id) throws SQLException {
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
        return new EndSite();
    }
}
