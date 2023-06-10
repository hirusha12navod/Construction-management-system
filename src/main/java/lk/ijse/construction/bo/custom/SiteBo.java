package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.model.SiteDto;

import java.sql.SQLException;
import java.util.List;

public interface SiteBo extends SuperBo {
    List<SiteDto> getAll() throws SQLException,ClassNotFoundException;
    List<String> loadIds() throws SQLException,ClassNotFoundException;
    SiteDto searchById(String id) throws SQLException,ClassNotFoundException;
}
