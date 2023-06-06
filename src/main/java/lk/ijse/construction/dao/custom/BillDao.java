package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.model.SalesDto;

import java.sql.SQLException;

public interface BillDao extends CrudDao<SalesDto, String> {
    boolean saveDetails(SalesDto dto) throws SQLException;
}
