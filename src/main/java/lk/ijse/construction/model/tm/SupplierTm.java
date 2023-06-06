package lk.ijse.construction.model.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SupplierTm {
    private String supplier_id;
    private String name;
    private int contact;
    private String item;
}
