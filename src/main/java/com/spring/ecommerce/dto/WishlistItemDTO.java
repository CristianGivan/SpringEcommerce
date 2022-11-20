package com.spring.ecommerce.dto;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.model.WishlistItem;

public class WishlistItemDTO {

    private Long productId;
    private Long userId;
    private String name;

    public WishlistItemDTO(Long productId, Long userId, String name) {
        this.productId = productId;
        this.userId = userId;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
