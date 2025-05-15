package com.example.ProductApplication.mapper;

import com.example.ProductApplication.dto.ProductDTO;
import com.example.ProductApplication.entity.Category;
import com.example.ProductApplication.entity.Product;

public class ProductMapper {

    //Entity to DTO
    public static ProductDTO toProductDTO(Product product){
//        return new ProductDTO(
//                product.getId(),
//            product.getName(),
//            product.getDescription(),
//                product.getPrice(),
//            product.getCategory().getId()
//
//        );

        ProductDTO productDTO=new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryId(product.getCategory().getId());

        return productDTO;


    }

    //DTO to Entity
    public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product=new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        return product;
    }

}
