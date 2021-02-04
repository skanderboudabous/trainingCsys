package tn.iit.storemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComposantDto {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String name;
    private Collection<MedicamentDto> medicaments;
}
