package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.model.TaskDto;
import uz.pdp.appcodingbat.repository.CategoryRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;
import uz.pdp.appcodingbat.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public ResponseEntity<?> getTaskById(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("Task not found",false));
        return ResponseEntity.ok(optionalTask.get());
    }

    public ResponseEntity<?> addTask(TaskDto dto){
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (!optionalCategory.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("category not found",false));
        Task task = new Task();
        task.setName(dto.getName());
        task.setExamples(dto.getExamples());
        task.setHasStar(dto.isHasStar());
        task.setSolution(dto.getSolution());
        task.setText(dto.getText());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);
        return ResponseEntity.ok(new ApiResponse("Task added", true));

    }

    public ResponseEntity<?> editTask(TaskDto dto, Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("Task not found",false));
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (!optionalCategory.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("Category not found",false));
        Task task = optionalTask.get();
        task.setName(dto.getName());
        task.setExamples(dto.getExamples());
        task.setHasStar(dto.isHasStar());
        task.setSolution(dto.getSolution());
        task.setText(dto.getText());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);
        return ResponseEntity.ok(new ApiResponse("Task edited", true));
    }

    public ResponseEntity<?> deleteTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("Task not found",false));
return ResponseEntity.ok(new ApiResponse("Task deleted", true));
    }

}
