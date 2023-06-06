package lk.ijse.construction.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDto {
    private String supplier_id;
    private String name;
    private int contact;
    private String item;
}
