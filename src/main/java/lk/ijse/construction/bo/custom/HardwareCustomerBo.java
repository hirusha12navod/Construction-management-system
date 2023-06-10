package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.HardwareCustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface HardwareCustomerBo extends SuperBo {
    List<HardwareCustomerDto> getAll() throws SQLException,ClassNotFoundException;
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    HardwareCustomerDto searchById(String id) throws SQLException,ClassNotFoundException;
    String getName(String id) throws SQLException,ClassNotFoundException;
}
