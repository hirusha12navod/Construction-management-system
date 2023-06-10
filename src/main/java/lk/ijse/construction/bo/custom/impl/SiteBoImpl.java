package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.SiteBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.SiteDao;
import lk.ijse.construction.entity.Site;
import lk.ijse.construction.model.SiteDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteBoImpl implements SiteBo {
    SiteDao siteDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SITE_DAO);

    @Override
    public List<SiteDto> getAll() throws SQLException, ClassNotFoundException {
        List<SiteDto> list = new ArrayList<>();
        for (Site site:siteDao.getAll()) {
            list.add(new SiteDto(
                    site.getSite_Id(),
                    site.getSite_Name(),
                    site.getLocation(),
                    site.getContact_person(),
                    site.getStart_date(),
                    site.getContactNo()
            ));
        }
        return list;
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return siteDao.loadIds();
    }

    @Override
    public SiteDto searchById(String id) throws SQLException, ClassNotFoundException {
        Site site = siteDao.searchById(id);
        return new SiteDto(
                site.getSite_Id(),
                site.getSite_Name(),
                site.getLocation(),
                site.getContact_person(),
                site.getStart_date(),
                site.getContactNo()
        );
    }
}
