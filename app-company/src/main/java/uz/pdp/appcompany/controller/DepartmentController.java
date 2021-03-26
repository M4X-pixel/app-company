package uz.pdp.appcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.model.CompanyDto;
import uz.pdp.appcompany.model.DepartmentDto;
import uz.pdp.appcompany.response.ApiResponse;
import uz.pdp.appcompany.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentController {

    DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(){
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Integer id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto dto){
        return departmentService.addDepartment(dto);
    }

    @PutMapping("/{id}")
     public ResponseEntity<?> editDepartment(@PathVariable Integer id,@RequestBody DepartmentDto dto){
        return departmentService.editDepartment(dto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer id){
       return departmentService.deleteDepartment(id);
    }

}
