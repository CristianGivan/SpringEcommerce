package com.spring.ecommerce;

import com.spring.ecommerce.Exceptions.CategoryNotFoundException;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.CategoryRepository;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.service.CategoryService;
import com.spring.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public Runner(CategoryRepository categoryRepository, ProductRepository productRepository,
                  ProductService productService, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("Category 1", "Category 1 description");
        Category category2 = new Category("Category 2", "Category 2 description");
        Category category3 = new Category("Category 3", "Category 3 description");
        Category category4 = new Category("Category 4", "Category 4 description");
        Category category5 = new Category("Category 5", "Category 5 description");
        Category category6 = new Category("Category 6", "Category 6 description");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        categoryRepository.save(category6);

        Product product1 = new Product("Product 1", "Product 1 description", 10.00);
        Product product2 = new Product("Product 2", "Product 2 description", 20.00);
        Product product3 = new Product("Product 3", "Product 3 description", 30.00);
        Product product4 = new Product("Product 4", "Product 4 description", 40.00);
        Product product5 = new Product("Product 5", "Product 5 description", 50.00);
        Product product6 = new Product("Product 6", "Product 6 description", 60.00);
        Product product7 = new Product("Product 7", "Product 7 description", 70.00);
        Product product8 = new Product("Product 8", "Product 8 description", 80.00);
        Product product9 = new Product("Product 9", "Product 9 description", 90.00);

        long categoryId = 1;
        Product toProduct = product1;
        extracted(categoryId, toProduct);

        categoryId = 1;
        toProduct = product2;
        extracted(categoryId, toProduct);

        categoryId = 1;
        toProduct = product3;
        extracted(categoryId, toProduct);

        categoryId = 2;
        toProduct = product4;
        extracted(categoryId, toProduct);

        categoryId = 2;
        toProduct = product5;
        extracted(categoryId, toProduct);

        categoryId = 3;
        toProduct = product6;
        extracted(categoryId, toProduct);

        categoryId = 3;
        toProduct = product7;
        extracted(categoryId, toProduct);

        categoryId = 4;
        toProduct = product8;
        extracted(categoryId, toProduct);

        categoryId = 5;
        toProduct = product9;
        extracted(categoryId, toProduct);

        //List<Product> productList=productService.getAllProducts();
        //System.out.println(productList);

    }

    private void extracted(long categoryId, Product toProduct) {
        Category addToCategory;
        addToCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
        toProduct.setCategory(addToCategory);
        addToCategory.getProductList().add(toProduct);
        categoryRepository.save(addToCategory);
    }
}
