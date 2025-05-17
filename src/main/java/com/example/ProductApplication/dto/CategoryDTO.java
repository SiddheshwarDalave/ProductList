package com.example.ProductApplication.dto;

import com.example.ProductApplication.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//Swagger notation
@Schema(
        name = "Category DTO",
        description = "It hold the Category information along with its products"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;

}

//@Data contain the @Gettter, setter, string