package com.upc.TuCine.TuCine.controller;


import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        return new ResponseEntity<Category>(categoryRepository.save(category), HttpStatus.CREATED);
    }





}
