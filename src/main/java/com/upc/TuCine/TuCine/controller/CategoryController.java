package com.upc.TuCine.TuCine.controller;


import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://backend-tucine-production.up.railway.app")
@RequestMapping("/api/TuCine/v1")

public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/categories
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<List<Category>>(categoryRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/categories
    //Method: POST
    @Transactional
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        existsCategoryByName(category.getName());
        validateCategory(category);
        return new ResponseEntity<Category>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new ValidationException("El nombre de la categoria es obligatorio");
        }
    }

    private void existsCategoryByName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ValidationException("No se puede registrar la categoria, ya existe una con ese nombre");
        }
    }




}
