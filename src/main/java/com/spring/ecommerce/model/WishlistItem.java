package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "wishlist_item")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlistItem_seq")
    @SequenceGenerator(name = "wishlistItem_seq",
            sequenceName = "wishlistItem_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "wishlistItem_id")
    public Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "product_product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "wishlist_wishlist_id")
    @JsonIgnore
    private Wishlist wishlist;

    public WishlistItem() {
    }

    public WishlistItem(String name, Product product, Wishlist wishlist) {
        this.name = name;
        this.product = product;
        this.wishlist = wishlist;
    }

    @Override
    public String toString() {
        return "WishlistItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productId=" + product.getId() +
                ", wishlist=" + wishlist +
                '}';
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
