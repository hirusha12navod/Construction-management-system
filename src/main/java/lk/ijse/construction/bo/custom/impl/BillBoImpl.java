package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.BillBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.BillDao;
import lk.ijse.construction.entity.SalesBillDetails;
import lk.ijse.construction.entity.Salesbill;
import lk.ijse.construction.model.SalesDetailsDto;
import lk.ijse.construction.model.SalesDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillBoImpl implements BillBo {
    BillDao billDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BILL_DAO);

    @Override
    public boolean save(SalesDto dto) throws SQLException, ClassNotFoundException {
        return billDao.save(new Salesbill(
                dto.getBillNum(),
                dto.getCustomer(),
                dto.getTotal()
        ));
    }

    @Override
    public boolean saveDetails(SalesDto dto) throws SQLException, ClassNotFoundException {
        List<SalesBillDetails> list = new ArrayList<>();
        for (SalesDetailsDto detail:dto.getDto()) {
            list.add(new SalesBillDetails(
                    detail.getBillNum(),
                    detail.getItemName(),
                    detail.getQty(),
                    detail.getUnitPrice()
            ));
        }
        return billDao.saveDetails(list);
    }
}
