package lk.ijse.construction.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Salesbill {
    private String billNum;
    private String customer;
    private double totalPrice;
}
