package com.upc.TuCine.TuCine.controller;


import com.upc.TuCine.TuCine.dto.CategoryDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/categories
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/categories
    //Method: POST
    @Transactional
    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        existsCategoryByName(categoryDto.getName());
        validateCategory(categoryDto);
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    private void validateCategory(CategoryDto category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new ValidationException("El nombre de la categoria es obligatorio");
        }
    }

    private void existsCategoryByName(String name) {
        if (categoryService.existsCategoryByName(name)) {
            throw new ValidationException("No se puede registrar la categoria, ya existe una con ese nombre");
        }
    }

}
