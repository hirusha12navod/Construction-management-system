package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.ConstructionBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.ConstructionDao;
import lk.ijse.construction.entity.Constructionst;
import lk.ijse.construction.model.MaterialDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstructionBoImpl implements ConstructionBo {
    ConstructionDao constructionDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CONSTRUCTION_DAO);

    @Override
    public double getStock(String material) throws SQLException, ClassNotFoundException {
        return constructionDao.getStock(material);
    }

    @Override
    public boolean updateStock(double stock, String name) throws SQLException, ClassNotFoundException {
        return constructionDao.updateStock(stock,name);
    }

    @Override
    public List<MaterialDto> getAll() throws SQLException, ClassNotFoundException {
        List<MaterialDto> list = new ArrayList<>();
        for (Constructionst entity:constructionDao.getAll()) {
            list.add(new MaterialDto(
                    entity.getMaterial_name(),
                    entity.getStock()
            ));
        }
        return list;
    }
}
