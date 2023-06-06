package lk.ijse.construction.dto;

import lombok.*;

import java.util.List;

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
