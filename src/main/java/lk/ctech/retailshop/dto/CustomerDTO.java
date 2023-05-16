package lk.ctech.retailshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {
    @NotNull
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    private String address;
    @NotNull
    private double salary;
}
