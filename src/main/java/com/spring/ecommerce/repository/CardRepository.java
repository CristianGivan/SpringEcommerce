package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.CardItem;
import com.spring.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardItem, Long> {
    List<CardItem> findAllByUser(User user);
    List<CardItem> findAllByUser_Id(Long id);
    List<CardItem> findAllByProduct_Id(Long id);
}
