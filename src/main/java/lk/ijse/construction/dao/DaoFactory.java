package lk.ijse.construction.dao;

import lk.ijse.construction.dao.custom.impl.BillDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public enum DaoType{
        BILL_DAO,
    }
    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case BILL_DAO: return (T) new BillDaoImpl();
            default:return null;
        }
    }
}
