package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);

    boolean existsCategoryByName(String name);
}
