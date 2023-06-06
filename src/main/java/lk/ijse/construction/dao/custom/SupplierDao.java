package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.model.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao extends CrudDao<SupplierDto, String> {
    List<String> getList(String sup) throws SQLException, ClassNotFoundException;;
}
