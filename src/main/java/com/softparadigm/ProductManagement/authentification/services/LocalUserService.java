package com.softparadigm.ProductManagement.authentification.services;

import com.softparadigm.ProductManagement.authentification.models.LocalUser;
import com.softparadigm.ProductManagement.authentification.repositories.LocalUserRepository;
import com.softparadigm.ProductManagement.shoppingcart.ShoppingCartService;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalUserService {
    final LocalUserRepository userRepository;
    final ShoppingCartService shoppingCartService;

    @Autowired
    public LocalUserService(LocalUserRepository userRepository, ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
    }

    public List<LocalUser> getUsers() {
        return userRepository.findAll();
    }

    // Should I create a User DTO or use manual referencing
    public LocalUser addUser(LocalUser user) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addShoppingCart();
        user.setShoppingCartID(shoppingCartDTO.getId());
        return userRepository.save(user);
    }

    public LocalUser getUserByEmailAndPassword(String email, String password){
        return userRepository.findUserByEmailAndPassword(email,password);
    }

    public String deleteUser(String userID) {
        userRepository.deleteById(userID);
        return "User with id " + userID + " has been deleted";
    }
}
