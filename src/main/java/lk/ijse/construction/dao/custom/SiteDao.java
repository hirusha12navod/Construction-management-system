package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.Site;
import lk.ijse.construction.model.SiteDto;

import java.sql.SQLException;
import java.util.List;

public interface SiteDao extends CrudDao<Site,String> {
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    Site searchById(String id) throws SQLException, ClassNotFoundException;
}
