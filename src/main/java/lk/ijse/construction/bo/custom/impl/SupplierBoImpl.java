package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.SupplierBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.SupplierDao;
import lk.ijse.construction.entity.Supplier;
import lk.ijse.construction.model.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBo {
    SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIER_DAO);

    @Override
    public List<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        List<SupplierDto> list = new ArrayList<>();
        for (Supplier supplier:supplierDao.getAll()) {
            list.add(new SupplierDto(
                    supplier.getSupplier_Id(),
                    supplier.getSupplier_name(),
                    supplier.getContact(),
                    supplier.getSupplyItems()
            ));
        }
        return list;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDao.save(
                new Supplier(
                        dto.getSupplier_id(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getItem()
                )
        );
    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDao.update(
                new Supplier(
                        dto.getSupplier_id(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getItem()
                )
        );
    }

    @Override
    public List<String> getList(String sup) throws SQLException, ClassNotFoundException {
        return supplierDao.getList(sup);
    }
}
