package com.spring.ecommerce.service;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private WishlistRepository wishlistRepository;
    private UserService userService;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, UserService userService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
    }

    public Wishlist createWishlistAtUser(String name, Long userId){
        User user =userService.findUserById(userId);
        Wishlist wishlist =new Wishlist(name,user);
        user.setWishlist(wishlist);
        return wishlistRepository.save(wishlist);
    }
}
