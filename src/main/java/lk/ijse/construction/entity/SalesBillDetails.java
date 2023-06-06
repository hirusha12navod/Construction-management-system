package lk.ijse.construction.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class SalesBillDetails {
    private String billNumber;
    private String itemName;
    private double qty;
    private double unitPrice;
}
