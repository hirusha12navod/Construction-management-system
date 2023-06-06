package lk.ijse.construction.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private String item_Id;
    private String item_name;
    private String item_category;
    private int initial_stock;
    private double price;
    private String rack_no;
}
