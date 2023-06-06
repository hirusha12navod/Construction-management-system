package lk.ijse.construction.dao.custom.impl;

import lk.ijse.construction.dao.custom.BillDao;
import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.entity.SalesBillDetails;
import lk.ijse.construction.entity.Salesbill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl implements BillDao {

    String SerialId = "";


    @Override
    public List<Salesbill> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Salesbill get() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Salesbill dto) throws SQLException, ClassNotFoundException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("INSERT INTO salesbill VALUE (?,?,?)");
        pstm.setString(1,dto.getBillNum());
        pstm.setString(2, dto.getCustomer());
        pstm.setDouble(3, dto.getTotalPrice());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Salesbill dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Salesbill salesDto) throws SQLException, ClassNotFoundException {
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
    public boolean saveDetails(List<SalesBillDetails> dto) throws SQLException, ClassNotFoundException {
        Connection con = DBconnection.getInstance().getConnection();
        Boolean saved = false;
        PreparedStatement pst = con.prepareStatement("INSERT INTO salesbilldetails VALUE (?,?,?,?)");
        for (int i = 0; i < dto.stream().count(); i++) {
            pst.setString(1,dto.get(i).getBillNumber());
            pst.setString(2,dto.get(i).getItemName());
            pst.setDouble(3,dto.get(i).getQty());
            pst.setDouble(4, dto.get(i).getUnitPrice());
            if(pst.executeUpdate()>0){
                saved = true;
            }
        }
        return saved;
    }
}
