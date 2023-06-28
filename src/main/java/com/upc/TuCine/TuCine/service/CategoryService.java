package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.CategoryDto;
import com.upc.TuCine.TuCine.dto.receive.CategoryReceiveDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryReceiveDto categoryReceiveDto);
}
