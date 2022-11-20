package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.WishlistItemDTO;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;
import com.spring.ecommerce.service.WishlistItemService;
import com.spring.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
    private WishlistService wishlistService;
    private WishlistItemService wishlistItemService;

    @Autowired
    public WishlistController(WishlistService wishlistService,
                              WishlistItemService wishlistItemService) {
        this.wishlistService = wishlistService;
        this.wishlistItemService = wishlistItemService;
    }

    @PostMapping("/create/{name}/{userId}")
    public Wishlist createWishlistAtUser(@PathVariable String name, @PathVariable Long userId) {
        return wishlistService.createWishlistAtUser(name, userId);
    }

    @PostMapping("/addwishlistitem")
    public WishlistItem addWishlistItem(@RequestBody WishlistItemDTO wishlistItemDTO) {
        return wishlistItemService.addWishlistToUser(wishlistItemDTO);
    }
    @GetMapping("/{userId}")
    public Wishlist findWishlistFromUser(@PathVariable Long userId){
        return wishlistService.findWishlistFromUser(userId);
    }


}
