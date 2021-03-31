package uz.pdp.appcompany.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "name cannot be empty")
    private String corpName;

    @NotNull(message = "director name cant be empty")
    private String directorName;

   private Integer addressId;

}
