package uz.pdp.appcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.model.CompanyDto;
import uz.pdp.appcompany.response.ApiResponse;
import uz.pdp.appcompany.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Integer id){
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        if (apiResponse.isSuccess()) return ResponseEntity.accepted().body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        if (apiResponse.isSuccess()) return ResponseEntity.accepted().body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

}
