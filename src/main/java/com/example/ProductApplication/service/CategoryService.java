package com.example.ProductApplication.service;

import com.example.ProductApplication.ResourceNotFoundException;
import com.example.ProductApplication.dto.CategoryDTO;
import com.example.ProductApplication.entity.Category;
import com.example.ProductApplication.exception.Sample;
import com.example.ProductApplication.mapper.CategoryMapper;
import com.example.ProductApplication.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    //business logic for category

    //used field injection here

    @Autowired
    private CategoryRepository categoryRepository;

    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category= CategoryMapper.toCategoryEntity(categoryDTO);
        category=categoryRepository.save(category);
        return  CategoryMapper.toCategoryDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryMapper::toCategoryDTO)
                .collect(Collectors.toList());

//        return categories.stream()
//                .map(cat->CategoryMapper.toCategoryDTO(cat))
//                .collect(Collectors.toList());

    }

    public CategoryDTO getCategoryById(Long id) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id: "+id));

        return CategoryMapper.toCategoryDTO(category);
    }

    public CategoryDTO deletecategory(Long id) {

        Category category= categoryRepository.findById(id)
                .orElseThrow(()->new Sample("Category With ID:"+id +"has been deleted already or not available in DB"));

        categoryRepository.deleteById(id);

        return CategoryMapper.toCategoryDTO(category);
    }




}
