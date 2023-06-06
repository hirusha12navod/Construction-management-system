package lk.ijse.construction.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Site {
    private String site_Id;
    private String site_Name;
    private String location;
    private int no_of_days;
    private String contact_person;
    private String start_date;
    private String end_date;
    private String contactNo;
}
