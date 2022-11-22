package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.ecommerce.Exceptions.IdAlreadyAllocatedException;
import com.spring.ecommerce.Exceptions.TotalPriceCalculationException;

import javax.persistence.*;

@Entity
@Table(name = "cardItem")
public class CardItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardItem_seq")
    @SequenceGenerator(name = "cardItem_seq",
            sequenceName = "cardItem_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    public CardItem() {
    }

    public CardItem(int quantity, User user, Product product) {
        this.quantity = quantity;
        this.user = user;
        this.product = product;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (this.id == null || this.id.equals(id)) {
            this.id = id;
        } else {
            throw new IdAlreadyAllocatedException(
                    "Id is already allocated cannot be changed");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", userId=" + user.getId() +
                ", productId=" + product.getId() +
                '}';
    }
}
