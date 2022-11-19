package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.UserNotFoundException;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public User findUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
    }
}
