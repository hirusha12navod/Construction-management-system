package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.entity.EndSite;

import java.sql.SQLException;
import java.util.List;

public interface EndSiteDao extends CrudDao<EndSite,String> {
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    EndSite searchById(String id) throws SQLException, ClassNotFoundException;
}
