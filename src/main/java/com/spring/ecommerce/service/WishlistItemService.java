package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.WishlistItemDTO;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.repository.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistItemService {

    private WishlistItemRepository wishlistItemRepository;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public WishlistItemService(WishlistItemRepository wishlistItemRepository,
                               UserService userService, ProductService productService) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public WishlistItem addWishlistToUser(WishlistItemDTO wishlistItemDTO) {
        Product product = productService.getProduct(wishlistItemDTO.getProductId());
        User user=userService.findUserById(wishlistItemDTO.getUserId());
        Wishlist wishlist =user.getWishlist();
        WishlistItem wishlistItem=new WishlistItem(wishlistItemDTO.getName(),
                product,wishlist);
        wishlist.getWishlistItems().add(wishlistItem);
        return wishlistItemRepository.save(wishlistItem);


    }

}
