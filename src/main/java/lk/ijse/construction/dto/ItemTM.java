package lk.ijse.construction.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemTM {
    private String item_Id;
    private String item_name;
    private Integer initial_stock;
    private Double price;
    private String rack_no;
}
