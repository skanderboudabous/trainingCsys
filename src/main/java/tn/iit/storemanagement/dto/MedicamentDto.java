package tn.iit.storemanagement.dto;

import lombok.*;
import tn.iit.storemanagement.domain.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentDto {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String name;
    @NotNull
    private float dosage;
    @NotNull
    private float price;
    @NotNull
    private Date expiredDate;
    @NotNull
    private String category;
}
