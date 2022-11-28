package com.spring.ecommerce.service;

import com.spring.ecommerce.Exceptions.UserNotFoundException;
import com.spring.ecommerce.dto.RegisterDTO;
import com.spring.ecommerce.model.Role;
import com.spring.ecommerce.model.RoleType;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.Wishlist;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found"));
    }
    public User addWishlistToUser(User user, Wishlist wishlist){
        user.setWishlist(wishlist);
       return userRepository.save(user);
    }
    public User register(RegisterDTO newUser) throws ResponseStatusException {
        User user =userRepository.findByUsername(newUser.getUsername());
        if(user!=null){
            throw new ResponseStatusException(HttpStatus.CREATED, "already exist");
        }
        user=new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role foundRole =roleService.findRoleByType(RoleType.ROLE_CLIENT);
        user.getRoleList().add(foundRole);
        foundRole.getUsers().add(user);

        return userRepository.save(user);
    }
}
