package lk.ijse.construction.bo.custom;

import lk.ijse.construction.bo.SuperBo;
import lk.ijse.construction.bo.custom.impl.BillBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }
    public enum BoType{
        BILL_BO
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case BILL_BO: return (T) new BillBoImpl();
            default: return null;
        }
    }
}
