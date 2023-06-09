package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.HardwareCustomerDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HardwareCustomerDaoImpl implements HardwareCustomerDao {


    String SerialId = "";


    @Override
    public List<Customer> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";

        List<Customer> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));
        }
        return data;
    }

    @Override
    public Customer get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Customer hardwareCustomerDto) throws SQLException, ClassNotFoundException {
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
        ResultSet resultSet = con.createStatement().executeQuery("SELECT customer_Id FROM customer");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
    @Override
    public Customer searchById(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM customer WHERE customer_Id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return null;
    }

    @Override
    public String getName(String id) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT name FROM customer WHERE customer_Id = ?");
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
