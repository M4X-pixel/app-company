package uz.pdp.appcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.model.WorkerDto;
import uz.pdp.appcompany.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    WorkerService workerService;

    @GetMapping
    public List<Worker> getWorkers(){
       return workerService.getWorkers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorker(@PathVariable Integer id){
    return     workerService.getWorkerById(id);
    }

    @PostMapping
    public ResponseEntity<?> addWorker(@RequestBody WorkerDto dto){
       return workerService.addWorker(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorker(@RequestBody WorkerDto dto, @PathVariable Integer id){
        return workerService.editWorker(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Integer id){
        return workerService.deleteWorker(id);
    }

}
