package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.ecommerce.Exceptions.IdAlreadyAllocatedException;

import javax.persistence.*;


@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItem_seq")
    @SequenceGenerator(name = "orderItem_seq",
            sequenceName = "orderItem_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

//    @Override
//    public String toString() {
//        return "OrderItem{" +
//                "id=" + id +
//                ", order=" + order +
//                ", product=" + product +
//                ", quantity=" + quantity +
//                '}';
//    }

    public void setId(Long id) {
        if (this.id == null || this.id.equals(id)) {
            this.id = id;
        } else {
            throw new IdAlreadyAllocatedException(
                    "Id is already allocated cannot be changed");
        }
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
