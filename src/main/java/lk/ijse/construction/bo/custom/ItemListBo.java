package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.ItemLDto;
import lk.ijse.construction.model.ItemsDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemListBo extends SuperBo {
    List<ItemLDto> getList(String id) throws SQLException,ClassNotFoundException;
    List<ItemsDto> getAll(String id) throws SQLException,ClassNotFoundException;
    double getPrice(String name) throws SQLException,ClassNotFoundException;
    int getQty(String name) throws SQLException,ClassNotFoundException;
    boolean updateStock(int qty,String name) throws SQLException,ClassNotFoundException;
    String getRack(String name) throws SQLException,ClassNotFoundException;
    List<ItemsDto> getAll() throws SQLException,ClassNotFoundException;
    ItemsDto searchById(String id) throws SQLException, ClassNotFoundException,ClassNotFoundException;
}
