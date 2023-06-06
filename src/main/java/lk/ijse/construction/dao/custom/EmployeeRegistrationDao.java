package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Employee;
import lk.ijse.construction.model.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRegistrationDao extends CrudDao<Employee,String> {
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    Employee searchById(String id) throws SQLException, ClassNotFoundException;
}
