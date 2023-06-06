package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.model.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRegistrationDao extends CrudDao<EmployeeDto,String> {
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    EmployeeDto searchById(String id) throws SQLException, ClassNotFoundException;
}
