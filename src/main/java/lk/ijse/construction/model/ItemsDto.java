package lk.ijse.construction.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemsDto {
    private String item_Id;
    private String item_name;
    private Integer initial_stock;
    private Double price;
    private String rack_no;
}
