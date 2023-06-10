package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.SiteDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.Site;
import lk.ijse.construction.model.SiteDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDaoImpl implements SiteDao {

    String SerialId = "";


    @Override
    public List<Site> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT site_Id,site_name,location,contact_person,start_date,contactNo FROM site";

        List<Site> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Site(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    0,
                    resultSet.getString(5),
                    resultSet.getString(6),
                    "",
                    resultSet.getString(8)
            ));
        }
        return data;
    }

    @Override
    public Site get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Site dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Site dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Site siteDto) throws SQLException, ClassNotFoundException {
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
        ResultSet resultSet = con.createStatement().executeQuery("SELECT site_Id FROM site");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public Site searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT site_Id,site_name,location,contact_person,start_date,contactNo FROM site WHERE site_Id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Site(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    0,
                    resultSet.getString(5),
                    resultSet.getString(6),
                    "",
                    resultSet.getString(8)
            );
        }
        return null;
    }
}
