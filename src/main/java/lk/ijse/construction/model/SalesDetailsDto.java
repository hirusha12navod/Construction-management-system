package lk.ijse.construction.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalesDetailsDto {
    private String billNum;
    private String itemName;
    private double qty;
    private double unitPrice;
}
