package com.example.ProductApplication.service;

import com.example.ProductApplication.dto.ProductDTO;
import com.example.ProductApplication.entity.Category;
import com.example.ProductApplication.entity.Product;
import com.example.ProductApplication.exception.CategoryNotFoundException;
import com.example.ProductApplication.mapper.ProductMapper;
import com.example.ProductApplication.repository.CategoryRepository;
import com.example.ProductApplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    //used constructor injection
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {

        Category category=categoryRepository.findById(productDTO.getCategoryId()).get();
        Product product= ProductMapper.toProductEntity(productDTO,category);
        productRepository.save(product);

        return ProductMapper.toProductDTO(product);
    }

    public List<ProductDTO> createProductAndShowList(ProductDTO productDTO) {

        Category category=categoryRepository.findById(productDTO.getCategoryId()).get();
        Product product= ProductMapper.toProductEntity(productDTO,category);
        productRepository.save(product);

        List<Product> listOfAllProducts=productRepository.findAll();



        return listOfAllProducts.stream()
                .map(ProductMapper::toProductDTO)
                .toList();
    }


    public List<ProductDTO> getAllProducts() {
      List<Product> list= productRepository.findAll();
      if(list.isEmpty()){
          return null;
      }
      List<ProductDTO> list2=list.stream()
              .map(ProductMapper::toProductDTO)
              .toList();

      return list2;
    }

    public ProductDTO getProductById(Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Product with Id:"+id+"Not avilable in the database"));

        return ProductMapper.toProductDTO(product);
    }

    public String deleteProductById(Long id) {

        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product with Id:"+id+"Not avilable in the database"));
        try{
            productRepository.deleteById(id);
            return "Product with Id:"+id+"has been deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product with Id:"+id+"Not avilable in the database"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Category category=categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("No category found with id:"+id));

        product.setCategory(category);
        productRepository.save(product);

        return ProductMapper.toProductDTO(product);
    }
}
