package lk.ijse.construction.bo.custom.impl;

import lk.ijse.construction.bo.custom.HardwareCustomerBo;
import lk.ijse.construction.dao.DaoFactory;
import lk.ijse.construction.dao.custom.HardwareCustomerDao;
import lk.ijse.construction.entity.Customer;
import lk.ijse.construction.model.HardwareCustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HardwareCustomerBoImpl implements HardwareCustomerBo {
    HardwareCustomerDao hardwareCustomerDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.HARDWARE_CUSTOMER_DAO);

    @Override
    public List<HardwareCustomerDto> getAll() throws SQLException, ClassNotFoundException {
        List<HardwareCustomerDto> list = new ArrayList<>();
        for (Customer c:hardwareCustomerDao.getAll()) {
            list.add(new HardwareCustomerDto(
                    c.getCustomer_Id(),
                    c.getName(),
                    c.getCustomer_Id(),
                    String.valueOf(c.getContact())
            ));
        }
        return list;
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return hardwareCustomerDao.loadIds();
    }

    @Override
    public HardwareCustomerDto searchById(String id) throws SQLException, ClassNotFoundException {
        Customer customer = hardwareCustomerDao.searchById(id);
        return new HardwareCustomerDto(
                customer.getCustomer_Id(),
                customer.getName(),
                customer.getCustomer_Id(),
                String.valueOf(customer.getContact())
        );
    }

    @Override
    public String getName(String id) throws SQLException, ClassNotFoundException {
        return hardwareCustomerDao.getName(id);
    }
}
