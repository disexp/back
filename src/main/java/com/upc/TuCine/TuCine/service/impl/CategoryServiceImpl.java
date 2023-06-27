package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.CategoryDto;
import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.repository.CategoryRepository;
import com.upc.TuCine.TuCine.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    CategoryServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    public CategoryDto EntityToDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category DtoToEntity(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = DtoToEntity(categoryDto);
        return EntityToDto(categoryRepository.save(category));
    }

    @Override
    public boolean existsCategoryByName(String name) {
        return categoryRepository.existsByName(name);
    }


}
