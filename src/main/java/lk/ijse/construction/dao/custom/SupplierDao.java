package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao extends CrudDao<Supplier, String> {
    List<String> getList(String sup) throws SQLException, ClassNotFoundException;;
}
