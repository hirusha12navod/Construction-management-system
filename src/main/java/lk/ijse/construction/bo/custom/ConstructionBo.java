package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.MaterialDto;

import java.sql.SQLException;
import java.util.List;

public interface ConstructionBo extends SuperBo {
    double getStock(String material) throws SQLException, ClassNotFoundException;
    boolean updateStock(double stock,String name) throws SQLException, ClassNotFoundException;
    List<MaterialDto> getAll() throws SQLException, ClassNotFoundException;

}
