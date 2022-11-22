package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.ecommerce.Exceptions.IdAlreadyAllocatedException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq",
            sequenceName = "order_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "order_id")
    public Long id;

    @Column (name = "created_date")
    private LocalDateTime cratedDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<OrderItem>orderItems;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cratedDate=" + cratedDate +
                ", totalPrice=" + totalPrice +
                ", userId=" + user.getId() +
                ", orderItems=" + orderItems +
                '}';
    }

    public Order(LocalDateTime cratedDate, Double totalPrice, User user,
                 List<OrderItem> orderItems) {
        this.cratedDate = cratedDate;
        this.totalPrice = totalPrice;
        this.user = user;
        this.orderItems = orderItems;
    }


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

    public LocalDateTime getCratedDate() {
        return cratedDate;
    }

    public void setCratedDate(LocalDateTime cratedDate) {
        this.cratedDate = cratedDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        if (orderItems== null){
            orderItems=new ArrayList<>();
        }
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
