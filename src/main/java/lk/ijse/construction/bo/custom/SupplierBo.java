package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBo extends SuperBo {
    List<SupplierDto> getAll() throws SQLException,ClassNotFoundException;
    boolean save(SupplierDto dto) throws SQLException,ClassNotFoundException;
    boolean update(SupplierDto dto) throws SQLException,ClassNotFoundException;
    List<String> getList(String sup) throws SQLException,ClassNotFoundException;
}
