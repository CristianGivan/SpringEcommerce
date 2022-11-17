package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.ProductNotFoundException;
import com.spring.ecommerce.Exceptions.UserNotFoundException;
import com.spring.ecommerce.dto.CardItemAndTotalPriceDTO;
import com.spring.ecommerce.dto.CardItemDTO;
import com.spring.ecommerce.model.CardItem;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.CardItemRepository;
import com.spring.ecommerce.repository.ProductRepository;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardItemService {
    private CardItemRepository cardItemRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public CardItemService(CardItemRepository cardItemRepository,
                           UserRepository userRepository, ProductRepository productRepository) {
        this.cardItemRepository = cardItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public CardItem addCardItem(CardItemDTO cardItemDTO) {
        User user = userRepository.findById(cardItemDTO.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        Product product = productRepository.findById(cardItemDTO.getProductId()).orElseThrow(
                () -> new ProductNotFoundException("Product not found"));
        CardItem cardItem = new CardItem(cardItemDTO.getQuantity(), user, product);
        return cardItemRepository.save(cardItem);
    }

    public List<CardItem> addCartItems(List<CardItemDTO> cardItemDTOList) {
        List<CardItem> cardItems = new ArrayList<>();
        for (CardItemDTO cardItemDTO : cardItemDTOList) {
            cardItems.add(addCardItem(cardItemDTO));
        }
        return cardItems;
    }

    public void deleCardItem(Long id) {
        CardItem cardItem = cardItemRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("nf"));
        cardItemRepository.delete(cardItem);
    }

    public List<Product> getAllProductsFromUser(Long userId) {
        /*
         * - Caut care este userul care are id
         * - caut toate card itemurile care au un user
         * - gasesc lista de produse cu acel di CardItem
         *   */
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        List<CardItem> cardItems = cardItemRepository.findAllByUser(user);
        List<Product> products = new ArrayList<>();
        for (CardItem cardItem : cardItems) {
            products.add(cardItem.getProduct());
        }

        return products;
    }

    public List<CardItem> getAllCartItemsFromUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        return cardItemRepository.findAllByUser(user);

    }

    public CardItemAndTotalPriceDTO getAllCardItemsAndTotalPriceFromUser(Long userId) {
        return new CardItemAndTotalPriceDTO(getAllCartItemsFromUser(userId));
    }
    public void deleteCardItemsFromUser(Long userId){
        /*
        * - gasesc lista de card itemuri de la user id
        * - intru la fiecare card item si il sterg
        * */
        List<CardItem> cardItemsOfUser =cardItemRepository.findAllByUser_Id(userId);
        for (CardItem cardItem : cardItemsOfUser){
            cardItemRepository.delete(cardItem);
        }
    }

}
