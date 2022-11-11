package com.spring.ecommerce.controller;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    public CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/create")
    public Category creteCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PostMapping("/createFromList")
    public List<Category> creteCategory(@RequestBody List<Category> categories) {
        return categoryService.saveAListOfCategories(categories);
    }
    @PutMapping("/update/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id){
        return categoryService.updateCategory(category, id);
    }
}
