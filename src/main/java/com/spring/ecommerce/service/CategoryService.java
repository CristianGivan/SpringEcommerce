package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.CategoryNotFoundException;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    public CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        Category foundCategory = categoryRepository.findByName(category.getName());
        if (foundCategory == null) {
            return categoryRepository.save(category);
        } else {
            throw new CategoryNotFoundException("The category is already exists");
            //throw new ResponseStatusException(HttpStatus.CREATED,"Category already exist");
        }

    }

    public List<Category> saveAListOfCategories(List<Category> categories) {
        List<Category> savedCategories = new ArrayList<>();
        Category foundCategory;
        for (Category category : categories) {
            foundCategory = categoryRepository.findByName(category.getName());
            if (foundCategory == null) {
                savedCategories.add(saveCategory(category));
            }
        }
        //System.out.println(savedCategories);
        //System.out.println("---");
        return savedCategories;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
