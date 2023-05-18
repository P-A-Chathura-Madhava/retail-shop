package lk.ctech.retailshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillDTO {
    private int billId;
    private int customerId;
    private int itemCode;
    private int qty;
    private int price;
}
