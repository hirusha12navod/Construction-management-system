package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Constructionst;
import lk.ijse.construction.model.MaterialDto;

import java.sql.SQLException;

public interface ConstructionDao extends CrudDao<Constructionst,String> {
    double getStock(String material) throws SQLException, ClassNotFoundException;
    boolean updateStock(double stock,String name) throws SQLException, ClassNotFoundException;
}
