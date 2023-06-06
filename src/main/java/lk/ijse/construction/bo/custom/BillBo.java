package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.SalesDto;

import java.sql.SQLException;

public interface BillBo extends SuperBo {
    boolean save(SalesDto dto) throws SQLException, ClassNotFoundException;
    boolean saveDetails(SalesDto dto) throws SQLException, ClassNotFoundException;
}
