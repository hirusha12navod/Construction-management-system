package lk.ijse.construction.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalesDto {
    private String billNum;
    private String customer;
    private double total;
    private List<SalesDetailsDto> dto;
}
