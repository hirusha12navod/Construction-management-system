package lk.ijse.construction.model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillTm {
    private String name;
    private int qty;
    private double amount;
}
