package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.entity.Prog_lang;
import uz.pdp.appcodingbat.model.CategoryDto;
import uz.pdp.appcodingbat.repository.CategoryRepository;
import uz.pdp.appcodingbat.repository.Prog_langRepository;
import uz.pdp.appcodingbat.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

@Autowired
    CategoryRepository categoryRepository;

@Autowired
    Prog_langRepository prog_langRepository;

public List<Category> getCategories(){
    return categoryRepository.findAll();
}

    public ResponseEntity<?> getCategoryById(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return ResponseEntity.status(409).body(new ApiResponse("Category not found",false));
        return ResponseEntity.ok(optionalCategory.get());
    }

    public ResponseEntity<?> addCategory(CategoryDto dto){
        Optional<Prog_lang> optionalProg_lang = prog_langRepository.findById(dto.getProgLangId());
        if (!optionalProg_lang.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Program language error",false));
        Category category = new Category(null,dto.getName(),dto.getDescription(),optionalProg_lang.get());
        categoryRepository.save(category);
        return ResponseEntity.ok(new ApiResponse("Category added", true)) ;
}
    public ResponseEntity<?> editCategory(CategoryDto dto, Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Category not found",false));
        Optional<Prog_lang> optionalProg_lang = prog_langRepository.findById(dto.getProgLangId());
        if (!optionalProg_lang.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Program language error",false));
        Category category = optionalCategory.get();
        category.setDescription(dto.getDescription());
        category.setName(dto.getName());
        category.setProgLang(optionalProg_lang.get());
        categoryRepository.save(category);
        return ResponseEntity.ok(new ApiResponse("Category edited", true));
    }

    public ResponseEntity<?> deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return ResponseEntity.status(409).body(
                new ApiResponse("Category not found",false));
        categoryRepository.delete(optionalCategory.get());
        return ResponseEntity.ok(new ApiResponse("Category deleted", true));
    }



}
