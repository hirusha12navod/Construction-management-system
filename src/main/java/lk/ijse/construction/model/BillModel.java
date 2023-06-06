package lk.ijse.construction.model;

import lk.ijse.construction.db.DBconnection;
import lk.ijse.construction.dto.SalesDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BillModel {

    String SerialId = "";



    public static Boolean save(SalesDto dto) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("INSERT INTO salesbill VALUE (?,?,?)");
        pstm.setString(1,dto.getBillNum());
        pstm.setString(2, dto.getCustomer());
        pstm.setDouble(3, dto.getTotal());

        return pstm.executeUpdate()>0;
    }

    public static Boolean saveDetails(SalesDto dto) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        Boolean saved = false;
        PreparedStatement pst = con.prepareStatement("INSERT INTO salesbilldetails VALUE (?,?,?,?)");
        for (int i = 0; i < dto.getDto().stream().count(); i++) {
            pst.setString(1,dto.getDto().get(i).getBillNum());
            pst.setString(2,dto.getDto().get(i).getItemName());
            pst.setDouble(3,dto.getDto().get(i).getQty());
            pst.setDouble(4, dto.getDto().get(i).getUnitPrice());
            if(pst.executeUpdate()>0){
                saved = true;
            }
        }
        return saved;
    }
}
