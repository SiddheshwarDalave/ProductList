package com.example.ProductApplication.controller;

import com.example.ProductApplication.dto.ProductDTO;
import com.example.ProductApplication.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Product related CRUD Operation",
        description = "This is product controller which has 6 Rest APis "
)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    //create
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);

    }
    //get product

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping("/list")
    public List<ProductDTO> createProductAndShowList(@RequestBody ProductDTO productDTO){
        return productService.createProductAndShowList(productDTO);
    }
    //get by Id

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //get by id
    //delete

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id){
        return productService.deleteProductById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }


}
