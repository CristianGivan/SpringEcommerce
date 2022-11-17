package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.ProductDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return product;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return productList;
    }

    @GetMapping("/fromCategory/{categoryId}")
    public List<Product> getAllProductsFromCategory(@PathVariable Long categoryId) {
        return productService.getAllProductsFromCategory(categoryId);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }


}
