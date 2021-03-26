package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.model.WorkerDto;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;
import uz.pdp.appcompany.repository.WorkerRepository;
import uz.pdp.appcompany.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getWorkers(){
        return workerRepository.findAll();
    }

    public ResponseEntity<?> getWorkerById(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("worker not found",false));
        return ResponseEntity.ok(optionalWorker.get());
    }

    public ResponseEntity<?> addWorker(WorkerDto dto){
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartmentId());
        if (!optionalDepartment.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("department not found",false));
        Address address = new Address(null, dto.getStreet(), dto.getHomeNumber());
        addressRepository.save(address);
        Worker worker = new Worker(null, dto.getName(), dto.getPhoneNumber(), address, optionalDepartment.get());
        workerRepository.save(worker);
        return ResponseEntity.ok(new ApiResponse("worker added",true));
    }

    public ResponseEntity<?> editWorker(Integer id, WorkerDto dto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("worker not found",false));
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartmentId());
        if (!optionalDepartment.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("department not found",false));
        Worker worker = optionalWorker.get();
        Address address = new Address(null, dto.getStreet(), dto.getHomeNumber());
        addressRepository.save(address);
        worker.setAddress(address);
        worker.setDepartment(optionalDepartment.get());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setName(dto.getName());
        workerRepository.save(worker);
        return ResponseEntity.accepted().body(new ApiResponse("worker edited", true));

    }

    public ResponseEntity<?> deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("worker not found",false));
        workerRepository.delete(optionalWorker.get());
        return ResponseEntity.ok(new ApiResponse("worker edited",true));
    }

}
