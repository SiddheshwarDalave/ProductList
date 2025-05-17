package com.example.ProductApplication.service;

import com.example.ProductApplication.exception.CategoryListIsEmptyException;
import com.example.ProductApplication.exception.ResourceNotFoundException;
import com.example.ProductApplication.dto.CategoryDTO;
import com.example.ProductApplication.entity.Category;
import com.example.ProductApplication.exception.CategoryAlreadyExistsException;
import com.example.ProductApplication.exception.SampleException;
import com.example.ProductApplication.mapper.CategoryMapper;
import com.example.ProductApplication.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    //business logic for category

    //used field injection here

    @Autowired
    private CategoryRepository categoryRepository;

    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        //Check for duplicate by CatergoryName
        Optional<Category> categoryOptional=categoryRepository.findByName(categoryDTO.getName());

        if(categoryOptional.isPresent()){
            //throw new RuntimeException("Category already exist");
            //adding custom exception
            throw new CategoryAlreadyExistsException("Category: " +categoryDTO.getName()+" already exist in the database");
        }
        //convert to Entity
        Category category= CategoryMapper.toCategoryEntity(categoryDTO);
        //Save to Database
        category=categoryRepository.save(category);
        //Return DTO by converting from entity to DTO
        return  CategoryMapper.toCategoryDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        // get list of categories from DB
        List<Category> categories = categoryRepository.findAll();

        //Check if list is empty
        if(categories.isEmpty()){
            throw new CategoryListIsEmptyException("No category detail available or not yet created");
        }

        return categories.stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();

//        Alternative -1
//        return categories.stream()
//                .map(CategoryMapper::toCategoryDTO)
//                .collect(Collectors.toList()); change 1

//        Alternative -2
//        return categories.stream()
//                .map(cat->CategoryMapper.toCategoryDTO(cat)) change 2
//                .collect(Collectors.toList());

    }

    public CategoryDTO getCategoryById(Long id) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id: "+id));

        return CategoryMapper.toCategoryDTO(category);
    }

    public CategoryDTO deletecategory(Long id) {

        Category category= categoryRepository.findById(id)
                .orElseThrow(()->new SampleException("Category With ID:"+id +"has been deleted already or not available in DB"));

        categoryRepository.deleteById(id);

        return CategoryMapper.toCategoryDTO(category);
    }




}
