package com.spring.ecommerce.dto;

import com.spring.ecommerce.Exceptions.TotalPriceCalculationException;
import com.spring.ecommerce.model.CardItem;

import java.util.List;

public class CardItemAndTotalPriceDTO {

    private Double totalPrice;
    private List<CardItem> cardItems;

    public CardItemAndTotalPriceDTO(List<CardItem> cardItems) {
        this.cardItems = cardItems;
        this.totalPrice = calculateTotalPriceOfCardItems();

    }

    @Override
    public String toString() {
        return "CardItemAndTotalPriceDTO{" +
                "totalPrice=" + totalPrice +
                ", cardItems=" + cardItems +
                '}';
    }

    public List<CardItem> getCardItems() {
        return cardItems;
    }

    public void setCardItems(List<CardItem> cardItems) {
        this.cardItems = cardItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Double calculateTotalPriceOfCardItems(){
        return cardItems.stream().
                map(x->x.getProduct().getPrice()* x.getQuantity()).
                reduce((s,p)->s+p).
                orElseThrow(()->new TotalPriceCalculationException("Price cannot be calculated"));
    }

}
