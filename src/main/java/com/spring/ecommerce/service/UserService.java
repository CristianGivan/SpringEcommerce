package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.UserNotFoundException;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found"));
    }
    public User addWishlistToUser(User user, Wishlist wishlist){
        user.setWishlist(wishlist);
       return userRepository.save(user);
    }
}
