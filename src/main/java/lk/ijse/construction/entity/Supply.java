package lk.ijse.construction.entity;

import lombok.*;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Supply {
    private String item_category;
    private String item_Id;
    private String item_name;
    private String qty;
    private String Date;
    private String supplier_name;
}
