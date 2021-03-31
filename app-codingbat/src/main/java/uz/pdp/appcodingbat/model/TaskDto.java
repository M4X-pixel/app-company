package uz.pdp.appcodingbat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private  String name,text,examples,solution;

    private boolean hasStar;

    private Integer categoryId;
}
