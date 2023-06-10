package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.HardwareItemAddBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.HardwareCustomerDao;
import lk.ijse.construction.dao.custom.HardwareItemAddDao;
import lk.ijse.construction.dao.custom.impl.HardwareItemAddDaoImpl;
import lk.ijse.construction.entity.Category;
import lk.ijse.construction.model.ItemCategoryDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HardwareItemAddBoImpl implements HardwareItemAddBo {
    HardwareItemAddDao hardwareItemAddDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.HARDWARE_ITEMS_ADD_DAO);

    @Override
    public List<ItemCategoryDto> getAll() throws SQLException, ClassNotFoundException {
        List<ItemCategoryDto> list = new ArrayList<>();
        for (Category category:hardwareItemAddDao.getAll()) {
            list.add(new ItemCategoryDto(
                    category.getItem_category()
            ));
        }
        return list;
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return hardwareItemAddDao.loadIds();
    }

    @Override
    public ItemCategoryDto searchById(String id) throws SQLException, ClassNotFoundException {
        Category category = hardwareItemAddDao.searchById(id);
        return new ItemCategoryDto(category.getItem_category());
    }
}
