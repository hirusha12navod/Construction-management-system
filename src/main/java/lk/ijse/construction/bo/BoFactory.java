package lk.ijse.construction.bo;

import lk.ijse.construction.bo.custom.impl.BillBoImpl;
import lk.ijse.construction.bo.custom.impl.ConstructionBoImpl;
import lk.ijse.construction.bo.custom.impl.EmployeeBoImpl;
import lk.ijse.construction.bo.custom.impl.HardwareCustomerBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }
    public enum BoType{
        BILL_BO,CONSTRUCTION_BO,EMPLOYEE_BO,END_SITE_BO,HARDWARE_CUSTOMER_BO
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case BILL_BO: return (T) new BillBoImpl();
            case CONSTRUCTION_BO: return (T) new ConstructionBoImpl();
            case EMPLOYEE_BO: return (T) new EmployeeBoImpl();
            case HARDWARE_CUSTOMER_BO: return (T) new HardwareCustomerBoImpl();
            default: return null;
        }
    }
}
