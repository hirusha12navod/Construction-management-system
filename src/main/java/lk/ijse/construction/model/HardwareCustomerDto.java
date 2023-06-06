package lk.ijse.construction.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class HardwareCustomerDto {
    private String customer_Id;
    private String name;
    private String serial_Id;
    private String contact;
}
