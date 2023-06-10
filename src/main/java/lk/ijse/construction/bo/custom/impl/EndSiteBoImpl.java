package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.EndSiteBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.EndSiteDao;
import lk.ijse.construction.entity.EndSite;
import lk.ijse.construction.model.EndSiteDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EndSiteBoImpl implements EndSiteBo {
    EndSiteDao endSiteDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.END_SITE_DAO);

    @Override
    public List<EndSiteDto> getAll() throws SQLException, ClassNotFoundException {
        List<EndSiteDto> list = new ArrayList<>();
        for (EndSite endSite:endSiteDao.getAll()) {
            list.add(new EndSiteDto(
                    endSite.getSite_name(),
                    endSite.getStart_date()
            ));
        }
        return list;
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return endSiteDao.loadIds();
    }

    @Override
    public EndSiteDto searchById(String id) throws SQLException, ClassNotFoundException {
        EndSite endSite = endSiteDao.searchById(id);
        return new EndSiteDto(
                endSite.getSite_name(),
                endSite.getStart_date()
        );
    }
}
