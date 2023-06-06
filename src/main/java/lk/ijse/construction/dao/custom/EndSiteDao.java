package lk.ijse.construction.dao.custom;

import lk.ijse.construction.dao.CrudDao;
import lk.ijse.construction.model.EndSiteDto;

import java.sql.SQLException;
import java.util.List;

public interface EndSiteDao extends CrudDao<EndSiteDto,String> {
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    EndSiteDto searchById(String id) throws SQLException, ClassNotFoundException;
}
