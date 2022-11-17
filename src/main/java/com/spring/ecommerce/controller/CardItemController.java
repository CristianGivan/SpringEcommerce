package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.CardItemAndTotalPriceDTO;
import com.spring.ecommerce.dto.CardItemDTO;
import com.spring.ecommerce.model.CardItem;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.CardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardItem")
public class CardItemController {
    private CardItemService cardItemService;

    @Autowired
    public CardItemController(CardItemService cardItemService) {
        this.cardItemService = cardItemService;
    }

    @PostMapping("/add")
    public CardItem addCardItem(@RequestBody CardItemDTO cardItemDTO) {
        return cardItemService.addCardItem(cardItemDTO);
    }
    @PostMapping("/add_list")
    public List<CardItem> addCardItems(@RequestBody List<CardItemDTO> cardItemDTOList) {
        return cardItemService.addCartItems(cardItemDTOList);
    }

    @DeleteMapping("/{id}")
    public void deleteCardItem(@PathVariable Long id) {
        cardItemService.deleCardItem(id);
    }

    @DeleteMapping("/from_user/{userId}")
    public void deleteCardItemsFromUser(@PathVariable Long userId){
        cardItemService.deleteCardItemsFromUser(userId);
    }


    @GetMapping("/from_user/{userId}")//todo q unde s-ar incadra cel mai bine aceasta metoda?
    public List<Product> getAllProductsFromUser(@PathVariable Long userId) {
        return cardItemService.getAllProductsFromUser(userId);
    }
    @GetMapping("/CI_from_user/{userId}")
    public List<CardItem> getAllCardItemsFromUser(@PathVariable Long userId) {
        //return cardItemService.getAllProductsFromUser(userId);
        return cardItemService.getAllCartItemsFromUser(userId);
    }
    @GetMapping("/from_user_and_total_price/{userId}")
    public CardItemAndTotalPriceDTO getAllCardItemsAndTotalPriceFromUser(@PathVariable Long userId) {
        //return cardItemService.getAllProductsFromUser(userId);
        return cardItemService.getAllCardItemsAndTotalPriceFromUser(userId);
    }


}
