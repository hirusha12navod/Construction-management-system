package lk.ijse.construction.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {
    private String emp_Id;
    private String designation;
    private String serial_Id;
    private String contact_no;
    private String status;
    private String nic;
    private String EmpName;
    private String EAddress;
}
