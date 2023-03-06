package com.softparadigm.ProductManagement.users.services;

import com.softparadigm.ProductManagement.users.entities.LocalUser;
import com.softparadigm.ProductManagement.shoppingcart.ShoppingCartService;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public abstract class AbstractLocalUserService {
    final MongoRepository userRepository;
    final ShoppingCartService shoppingCartService;

    public MongoRepository getUserRepository() {
        return userRepository;
    }

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    @Autowired
    public AbstractLocalUserService(MongoRepository userRepository, ShoppingCartService shoppingCartService) {
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
        return (LocalUser) userRepository.save(user);
    }


    public String deleteUser(String userID) {
        userRepository.deleteById(userID);
        return "User with id " + userID + " has been deleted";
    }


    public LocalUser getUserByID(String id) {
        return (LocalUser) userRepository.findById(id).get();
    }
}
