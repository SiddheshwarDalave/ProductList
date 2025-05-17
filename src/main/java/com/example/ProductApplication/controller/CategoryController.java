package com.example.ProductApplication.controller;

import com.example.ProductApplication.dto.CategoryDTO;
import com.example.ProductApplication.entity.Category;
import com.example.ProductApplication.exception.CategoryAlreadyExistsException;
import com.example.ProductApplication.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="Category related CRUD Operation",
        description = "This is Category controller which has 4 Rest APis "
)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //get all categories
    //create cat

    //Swagger notation
    @Operation(
            summary = "Create the Category",
            description = " Rest API for creating the categories"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category Created"
    )
    // Authorize part
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        // exception is handling by globalException so no need to write anything in this class

        // method 1
      // return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatusCode.valueOf(201));

       //or Method 2
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));


       //     Below part not required if global exception is written // used try catch
//        try{
//           CategoryDTO savedcategory=categoryService.createCategory(categoryDTO);
//           return ResponseEntity.status(HttpStatus.CREATED).body(savedcategory);
//        }catch (CategoryAlreadyExistsException ex){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//        }

    }

    //Get all Categories
    @GetMapping
    public List<CategoryDTO> getAll(){
        return categoryService.getAllCategories();
    }

    //get cat by id

    @GetMapping("/{id}")
    public CategoryDTO GetCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //Delete Cat by Id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public CategoryDTO deleteCategory(@PathVariable Long id){
        return categoryService.deletecategory(id);
    }
}
