package com.spring.ecommerce.service;

import com.spring.ecommerce.model.CardItem;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.OrderItem;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CardService cardService;
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CardService cardService,
                        UserService userService) {
        this.orderRepository = orderRepository;
        this.cardService = cardService;
        this.userService = userService;
    }

    public Order placeOrder(Long userId) {
        //luam de la utilizator ce are in carditem si sa puem in order
        Order order =new Order();
        OrderItem orderItem = new OrderItem();
        LocalDateTime createdDate = LocalDateTime.now();
        User user = userService.findUserById(userId);
        List<CardItem> cardItems = cardService.getAllCartItemsFromUser(userId);
        Double totalPrice = cardService.calculateTotalPriceOfCardItems(cardItems);
        List<OrderItem> orderItems=createOrderItemFromCardItem(cardItems,order);
        //order =new Order(createdDate, totalPrice, user, orderItems);

        order.setCratedDate(createdDate);
        order.setTotalPrice(totalPrice);
        order.setUser(user);

        cardService.deleteCardItemsFromUser(userId);
        Order newOrder= orderRepository.save(order);
        return newOrder;
    }
//todo oare cum as putea sa o fac mai buna metoda
    //nu as vrea sa adaug sa am argumentul la order
    //nu ar trebui sa fac get si add la ordere in aceasta metoda
    public List<OrderItem> createOrderItemFromCardItem(List<CardItem> cardItems,Order order) {
        //todo daca definesc orderul aici nu merge dar daca il definesc in foe merge
        //de ce trebuie ce se intampla asta cand dau add in lista nu se creaza un nou obiect in heap?
        //OrderItem orderItem=new OrderItem();
        List<OrderItem> orderItems=new ArrayList<>();
        for (CardItem cardItem : cardItems) {
            OrderItem orderItem=new OrderItem();
            orderItem.setQuantity(cardItem.getQuantity());
            orderItem.setProduct(cardItem.getProduct());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            order.getOrderItems().add(orderItem);
        }
        return orderItems;
    }
        public  List<Order> findAllOrdersByUserAndOrderByDate(Long userId){
        User user = userService.findUserById(userId);
        List<Order> orders =orderRepository.findAllByUser(user);
        return orders;
        }
        public Order findOrderById(Long id){
        return orderRepository.findAllById(id);
        }

}
