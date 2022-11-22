package com.spring.ecommerce;

import com.spring.ecommerce.Exceptions.CategoryNotFoundException;
import com.spring.ecommerce.model.*;
import com.spring.ecommerce.repository.*;
import com.spring.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ProductService productService;
    private CategoryService categoryService;
    private UserRepository userRepository;
    private UserService userService;
    private CardRepository cardItemRepository;
    private CardService cardItemService;
    private WishlistRepository wishlistRepository;
    private WishlistService wishlistService;
    private WishlistItemRepository wishlistItemRepository;
    private WishlistItemService wishlistItemService;
    private RoleRepository roleRepository;



    @Autowired
    public Runner(CategoryRepository categoryRepository, ProductRepository productRepository,
                  ProductService productService, CategoryService categoryService,
                  UserRepository userRepository, UserService userService, CardRepository cardItemRepository,
                  CardService cardItemService, WishlistRepository wishlistRepository,
                  WishlistService wishlistService, WishlistItemRepository wishlistItemRepository,
                  WishlistItemService wishlistItemService,RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.cardItemRepository = cardItemRepository;
        this.cardItemService = cardItemService;
        this.wishlistRepository = wishlistRepository;
        this.wishlistService = wishlistService;
        this.wishlistItemRepository = wishlistItemRepository;
        this.wishlistItemService = wishlistItemService;
        this.roleRepository=roleRepository;
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
        extracted(categoryId, product1);

        Product toProduct;
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


        User user1 = new User("User 1",
                "$2a$12$sJZ7/SZpCOTSeMg1jos87ulm.OcN31uQKnYisY/5r5XlNXSoQKPRi");
        User user2 = new User("User 2",
                "$2a$12$gaUsXx4r4JlzHYXomu/XguBgQZbj2XXOWq5h683u7KCOPUozoRy56");
        User user3 = new User("User 3",
                "$2a$12$b/jcMc9LC8sTk.8mV6Mzv.0GFCqgD7O/oK6m96nojFVKuGj8LlJv2");

        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);


        Product savedProduct1 = productRepository.findProductById(1l);
        Product savedProduct2 = productRepository.findProductById(2l);
        Product savedProduct3 = productRepository.findProductById(3l);
        Product savedProduct4 = productRepository.findProductById(4l);
        Product savedProduct5 = productRepository.findProductById(5l);
        Product savedProduct6 = productRepository.findProductById(6l);

        CardItem cardItem1 = new CardItem(1, savedUser1, savedProduct1);
        CardItem cardItem2 = new CardItem(2, savedUser1, savedProduct2);
        CardItem cardItem3 = new CardItem(3, savedUser1, savedProduct3);
        CardItem cardItem4 = new CardItem(4, savedUser2, savedProduct4);
        CardItem cardItem5 = new CardItem(5, savedUser2, savedProduct5);
        CardItem cardItem6 = new CardItem(6, savedUser3, savedProduct6);


        cardItemRepository.save(cardItem1);
        cardItemRepository.save(cardItem2);
        cardItemRepository.save(cardItem3);
        cardItemRepository.save(cardItem4);
        cardItemRepository.save(cardItem5);
        cardItemRepository.save(cardItem6);

        List<User> adminUsers=new ArrayList<>();
        adminUsers.add(savedUser1);
        adminUsers.add(savedUser3);
        List<User> clientUsers=new ArrayList<>();
        clientUsers.add(savedUser2);
        clientUsers.add(savedUser3);

        Role adminRole=new Role(RoleType.ROLE_ADMIN,adminUsers);
        Role clientRole=new Role(RoleType.ROLE_CLIENT,clientUsers);

        roleRepository.save(adminRole);
        roleRepository.save(clientRole);


// todo nu reusesc sa fac corect One to One
//        Wishlist wishlist1 = new Wishlist("wishlist1", savedUser1);
//        savedUser1.setWishlist(wishlist1);
//        wishlistRepository.save(wishlist1);
//
//        Wishlist wishlist2 = new Wishlist("wishlist2", savedUser2);
//        savedUser1.setWishlist(wishlist2);
//        wishlistRepository.save(wishlist2);
//        System.out.println(user1);
//        System.out.println(user2);
//        System.out.println(user3);

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