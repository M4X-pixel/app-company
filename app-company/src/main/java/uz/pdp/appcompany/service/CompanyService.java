package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.model.CompanyDto;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.CompanyRepository;
import uz.pdp.appcompany.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    ApiResponse apiResponse;

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    public ResponseEntity<?> getCompanyById(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            apiResponse.setMessage("Company not found");
            apiResponse.setSuccess(false);
            return ResponseEntity.status(409).body(apiResponse);
        }
        return ResponseEntity.ok(optionalCompany.get());
    }

    public ApiResponse addCompany(CompanyDto companyDto){
        Company company = new Company();
        try{
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());
             Address address = new Address(null, companyDto.getStreet(), companyDto.getHomeNumber());
            addressRepository.save(address);
            company.setAddress(address);
            companyRepository.save(company);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Company added");
            return apiResponse;
        }
        catch(Exception e) {
            return new ApiResponse("xatolik",false);
        }
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())return new ApiResponse("Company not found",false);
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address = new Address(null, companyDto.getStreet(), companyDto.getHomeNumber());
        addressRepository.save(address);
        company.setAddress(address);
        companyRepository.save(company);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Company edited");
        return apiResponse;
    }
    public ApiResponse deleteCompany(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())return new ApiResponse("Company not found",false);
        companyRepository.delete(optionalCompany.get());
        return new ApiResponse("Company deleted",true);
    }

}
