package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.EndSiteDto;

import java.sql.SQLException;
import java.util.List;

public interface EndSiteBo extends SuperBo {
    List<EndSiteDto> getAll() throws SQLException,ClassNotFoundException;
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    EndSiteDto searchById(String id) throws SQLException,ClassNotFoundException;
}
