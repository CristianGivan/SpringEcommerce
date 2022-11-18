package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.UserNotFoundException;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
    }
}
