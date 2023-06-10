package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Category;
import lk.ijse.construction.model.ItemCategoryDto;

import java.sql.SQLException;
import java.util.List;

public interface HardwareItemAddDao extends CrudDao<Category,String> {
    List<Category> getAll() throws SQLException,ClassNotFoundException;
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    Category searchById(String id) throws SQLException,ClassNotFoundException;
}
