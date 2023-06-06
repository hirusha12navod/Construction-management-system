package lk.ijse.construction.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SiteDto {
    private String site_Id;
    private String site_name;
    private String location;
    private String contact_person;
    private String start_date;
    private String contactNo;
}
