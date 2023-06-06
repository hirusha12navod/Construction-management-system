package lk.ijse.construction.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.construction.dao.custom.ConstructionDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.Constructionst;
import lk.ijse.construction.model.MaterialDto;
import lk.ijse.construction.model.tm.MaterialTm;

import java.sql.*;
import java.util.List;

public class ConstructionDaoImpl implements ConstructionDao {

    String SerialId = "";

    @Override
    public double getStock(String material) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT stock FROM constructionst WHERE material_name = ?");
        pstm.setString(1,material);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return  resultSet.getDouble(1);
        }
        return 0;
    }

    @Override
    public boolean updateStock(double stock,String name) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("UPDATE constructionst SET stock=? WHERE material_name=?");
        pstm.setDouble(1,stock);
        pstm.setString(2,name);
        return pstm.executeUpdate()>0;
    }

    @Override
    public List<Constructionst> getAll() throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM constructionst");
        ObservableList<Constructionst> list = FXCollections.observableArrayList();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(new Constructionst(
                    resultSet.getString(1),
                    resultSet.getDouble(2)
            ));
        }
        return list;
    }

    @Override
        public Constructionst get() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Constructionst dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Constructionst dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Constructionst materialDto) throws SQLException, ClassNotFoundException {
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
