package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.model.SiteDto;

import java.sql.SQLException;
import java.util.List;

public interface SiteDao extends CrudDao<SiteDto,String> {
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    SiteDto searchById(String id) throws SQLException, ClassNotFoundException;
}
