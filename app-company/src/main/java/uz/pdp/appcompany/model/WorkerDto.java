package uz.pdp.appcompany.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private String name;
    private Integer phoneNumber,departmentId;
    private Integer addressId;



}
