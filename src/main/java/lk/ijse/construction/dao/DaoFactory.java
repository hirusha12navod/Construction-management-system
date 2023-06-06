package lk.ijse.construction.dao;

import lk.ijse.construction.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public enum DaoType{
        BILL_DAO,CONSTRUCTION_DAO,EMPLOYEE_REGISTRATION_DAO,END_SITE_DAO,HARDWARE_CUSTOMER_DAO,HARDWARE_ITEMS_ADD_DAO
    }
    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case BILL_DAO: return (T) new BillDaoImpl();
            case CONSTRUCTION_DAO: return (T) new ConstructionDaoImpl();
            case EMPLOYEE_REGISTRATION_DAO: return (T) new EmployeeRegistrationDaoImpl();
            case END_SITE_DAO: return (T) new EndSiteDaoImpl();
            case HARDWARE_CUSTOMER_DAO: return (T) new HardwareCustomerDaoImpl();
            case HARDWARE_ITEMS_ADD_DAO: return (T) new HardwareItemAddDaoImpl();
            default:return null;
        }
    }
}
