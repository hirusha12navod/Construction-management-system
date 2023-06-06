package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.bo.custom.impl.BillBoImpl;
import lk.ijse.construction.bo.custom.impl.ConstructionBoImpl;
import lk.ijse.construction.bo.custom.impl.EmployeeBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }
    public enum BoType{
        BILL_BO,CONSTRUCTION_BO,EMPLOYEE_BO
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case BILL_BO: return (T) new BillBoImpl();
            case CONSTRUCTION_BO: return (T) new ConstructionBoImpl();
            case EMPLOYEE_BO: return (T) new EmployeeBoImpl();
            default: return null;
        }
    }
}
