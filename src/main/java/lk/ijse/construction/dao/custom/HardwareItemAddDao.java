package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.model.ItemCategoryDto;

import java.sql.SQLException;
import java.util.List;

public interface HardwareItemAddDao extends CrudDao<ItemCategoryDto,String> {
    List<ItemCategoryDto> getAll() throws SQLException,ClassNotFoundException;
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    ItemCategoryDto searchById(String id) throws SQLException,ClassNotFoundException;
}
