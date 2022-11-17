package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.CardItem;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //todo q1 de ce nu imi merge sa apelez CardItem_ProductID si ce ii CardItem_Entity?
    //vezi ProductService-getallP...
    List<Product> findAllByCategory(Category category);
    //List<Product> findAllByCardItems(List<CardItem> cardItems);
    //Product findProductByCardItem(CardItem cardItem);

    Product findProductById(Long id);
}
