package lk.ijse.construction.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Supplier {
    private String supplier_Id;
    private String supplier_name;
    private int contact;
    private String supplyItems;
}
