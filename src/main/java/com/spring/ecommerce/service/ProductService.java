package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.CategoryNotFoundException;
import com.spring.ecommerce.Exceptions.ProductNotFoundException;
import com.spring.ecommerce.dto.ProductDTO;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.CategoryRepository;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product getProduct(Long id) {
        System.out.println(id);
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found"));
        return productRepository.save(product);

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsFromCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
        return productRepository.findAllByCategory(category);
    }

    public Product saveProduct(ProductDTO productDTO) {
        Long categoryId = productDTO.getCategoryId();
        Category addToCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
        Product product = new Product(productDTO.getName(),
                productDTO.getDescription(), productDTO.getPrice(),addToCategory);
        addToCategory.getProductList().add(product);
        return productRepository.save(product);
    }
    public Product updateProduct(Long id,ProductDTO productDTO) {
        Product product=productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException("Product not found"));
        Long categoryId = productDTO.getCategoryId();
        Category addToCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
        product.setId(id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(addToCategory);
        addToCategory.getProductList().add(product);
        return productRepository.save(product);
    }
}
