package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Customer;
import lk.ijse.construction.model.HardwareCustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface HardwareCustomerDao extends CrudDao <Customer,String>{
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    Customer searchById(String id) throws SQLException,ClassNotFoundException;
    String getName(String id) throws SQLException, ClassNotFoundException;
}
