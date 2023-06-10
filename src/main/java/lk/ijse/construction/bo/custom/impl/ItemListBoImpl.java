package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.ItemListBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.ItemListDao;
import lk.ijse.construction.entity.Item;
import lk.ijse.construction.model.ItemLDto;
import lk.ijse.construction.model.ItemsDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemListBoImpl implements ItemListBo {
    ItemListDao itemListDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ITEM_LIST_DAO);

    @Override
    public List<ItemLDto> getList(String id) throws SQLException, ClassNotFoundException {
        List<ItemLDto> list = new ArrayList<>();
        for (Item i:itemListDao.getList(id)) {
            list.add(new ItemLDto(
                    i.getItem_name()
            ));
        }
        return list;
    }

    @Override
    public List<ItemsDto> getAll(String id) throws SQLException, ClassNotFoundException {
        List<ItemsDto> list = new ArrayList<>();
        for (Item item:itemListDao.getAll(id)) {
            list.add(new ItemsDto(
                    item.getItem_Id(),
                    item.getItem_name(),
                    item.getInitial_stock(),
                    item.getPrice(),
                    item.getRack_no()
            ));
        }
        return list;
    }

    @Override
    public double getPrice(String name) throws SQLException, ClassNotFoundException {
        return itemListDao.getPrice(name);
    }

    @Override
    public int getQty(String name) throws SQLException, ClassNotFoundException {
        return itemListDao.getQty(name);
    }

    @Override
    public boolean updateStock(int qty, String name) throws SQLException, ClassNotFoundException {
        return itemListDao.updateStock(qty,name);
    }

    @Override
    public String getRack(String name) throws SQLException, ClassNotFoundException {
        return itemListDao.getRack(name);
    }

    @Override
    public List<ItemsDto> getAll() throws SQLException, ClassNotFoundException {
        List<ItemsDto> list = new ArrayList<>();
        for (Item item:itemListDao.getAll()) {
            list.add(new ItemsDto(
                    item.getItem_Id(),
                    item.getItem_name(),
                    item.getInitial_stock(),
                    item.getPrice(),
                    item.getRack_no()
            ));
        }
        return list;
    }

    @Override
    public ItemsDto searchById(String id) throws SQLException, ClassNotFoundException, ClassNotFoundException {
        Item item = itemListDao.searchById(id);
        return new ItemsDto(
                item.getItem_Id(),
                item.getItem_name(),
                item.getInitial_stock(),
                item.getPrice(),
                item.getRack_no()
        );
    }
}
