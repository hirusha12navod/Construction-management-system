package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.ItemCategoryDto;

import java.sql.SQLException;
import java.util.List;

public interface HardwareItemAddBo extends SuperBo {
    List<ItemCategoryDto> getAll() throws SQLException,ClassNotFoundException;
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    ItemCategoryDto searchById(String id) throws SQLException,ClassNotFoundException;
}
