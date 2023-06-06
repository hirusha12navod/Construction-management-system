package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.SalesBillDetails;
import lk.ijse.construction.entity.Salesbill;
import lk.ijse.construction.model.SalesDto;

import java.sql.SQLException;
import java.util.List;

public interface BillDao extends CrudDao<Salesbill, String> {
    boolean saveDetails(List<SalesBillDetails> dto) throws SQLException, ClassNotFoundException;
}
