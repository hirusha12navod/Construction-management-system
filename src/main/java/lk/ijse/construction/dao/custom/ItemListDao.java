package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Item;
import lk.ijse.construction.model.ItemLDto;
import lk.ijse.construction.model.ItemsDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemListDao extends CrudDao<Item,String> {
    List<Item> getList(String id) throws SQLException, ClassNotFoundException;
    double getPrice(String name) throws SQLException, ClassNotFoundException;
    int getQty(String name) throws SQLException, ClassNotFoundException;
    boolean updateStock(int qty,String name) throws SQLException, ClassNotFoundException;
    String getRack(String name) throws SQLException, ClassNotFoundException;
    List<Item> getAll(String id) throws SQLException, ClassNotFoundException;
    Item searchById(String id) throws SQLException, ClassNotFoundException;
}
