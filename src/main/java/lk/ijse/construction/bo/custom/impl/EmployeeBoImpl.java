package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.EmployeeBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.EmployeeRegistrationDao;
import lk.ijse.construction.entity.Employee;
import lk.ijse.construction.model.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {
    EmployeeRegistrationDao employeeRegistrationDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE_REGISTRATION_DAO);

    @Override
    public List<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee entity:employeeRegistrationDao.getAll()) {
            list.add(new EmployeeDto(
                    entity.getEmp_Id(),
                    entity.getDesignation(),
                    entity.getEmp_Id(),
                    entity.getContact_no(),
                    entity.getStatus(),
                    entity.getNic(),
                    entity.getEmpName(),
                    entity.getEAddress()
            ));
        }
        return list;
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return employeeRegistrationDao.loadIds();
    }

    @Override
    public EmployeeDto searchById(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeRegistrationDao.searchById(id);
        return new EmployeeDto(
                employee.getEmp_Id(),
                employee.getDesignation(),
                employee.getEmp_Id(),
                employee.getContact_no(),
                employee.getStatus(),
                employee.getNic(),
                employee.getEmpName(),
                employee.getEAddress()
        );
    }
}
