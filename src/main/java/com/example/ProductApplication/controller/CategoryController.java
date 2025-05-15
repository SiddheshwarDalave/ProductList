package com.example.ProductApplication.controller;

import com.example.ProductApplication.dto.CategoryDTO;
import com.example.ProductApplication.entity.Category;
import com.example.ProductApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //get all categories
    //create cat
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public List<CategoryDTO> getAll(){
        return categoryService.getAllCategories();
    }

    //get cat by id

    @GetMapping("/{id}")
    public CategoryDTO GetCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public CategoryDTO deleteCategory(@PathVariable Long id){
        return categoryService.deletecategory(id);
    }
}
