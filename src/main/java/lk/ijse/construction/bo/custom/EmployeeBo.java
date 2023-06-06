package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBo extends SuperBo {
    List<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    EmployeeDto searchById(String id) throws SQLException, ClassNotFoundException;
}
