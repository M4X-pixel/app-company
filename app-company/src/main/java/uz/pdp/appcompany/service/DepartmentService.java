package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.model.DepartmentDto;
import uz.pdp.appcompany.repository.CompanyRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;
import uz.pdp.appcompany.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartments(){
    return     departmentRepository.findAll();
    }

    public ResponseEntity<?> getDepartmentById(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Department not found",false));
        return ResponseEntity.ok(optionalDepartment.get());
    }

    public ResponseEntity<?> addDepartment(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Company not found",false));
        Department department = new Department(null, departmentDto.getName(), optionalCompany.get());
        departmentRepository.save(department);
        return ResponseEntity.ok(new ApiResponse("Department added", true));
    }

    public ResponseEntity<?> editDepartment(DepartmentDto departmentDto, Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Company not found",false));
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Department not found",false));
        Department department = new Department(null, departmentDto.getName(), optionalCompany.get());
        departmentRepository.save(department);
        return ResponseEntity.ok(new ApiResponse("Department edited", true));
    }

    public ResponseEntity<?> deleteDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Department not found",false));
        departmentRepository.delete(optionalDepartment.get());
        return ResponseEntity.ok(new ApiResponse("Department deleted", true));
    }
}
