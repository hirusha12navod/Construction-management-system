package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.SupplierDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.model.SupplierDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {


    @Override
    public List<SupplierDto> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier";
        PreparedStatement lstm=con.prepareStatement(sql);

        List<SupplierDto> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    @Override
    public SupplierDto get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "INSERT INTO supplier VALUES(?,?,?,?) ";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1, dto.getSupplier_id());
        lstm.setString(2, dto.getName());
        lstm.setInt(3, dto.getContact());
        lstm.setString(4, dto.getItem());

        return lstm.executeUpdate()>0;

    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "UPDATE supplier SET supplier_name = ?,contact=?,supplyItems=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1, dto.getName());
        lstm.setString(2, dto.getSupplier_id());
        lstm.setInt(3, dto.getContact());

        return lstm.executeUpdate()>0;
    }

    @Override
    public boolean exists(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
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
    public List<String> getList(String sup) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT supplier_name FROM supplier WHERE supplyItems=?";
        PreparedStatement lstm=con.prepareStatement(sql);
        lstm.setString(1,sup);

        List<String> data = new ArrayList<>();

        ResultSet resultSet = lstm.executeQuery();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
