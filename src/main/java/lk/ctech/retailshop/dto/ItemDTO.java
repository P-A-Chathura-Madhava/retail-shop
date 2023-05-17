package lk.ctech.retailshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {
    @NotNull
    private int code;
    @NotNull
    private String description;
    @NotNull
    private double unitPrice;
    @NotNull
    private int qtyOnHand;
}
