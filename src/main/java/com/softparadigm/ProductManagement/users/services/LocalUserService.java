package com.softparadigm.ProductManagement.users.services;

import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import com.softparadigm.ProductManagement.users.dtos.UserDTO;
import com.softparadigm.ProductManagement.users.entities.LocalUser;
import com.softparadigm.ProductManagement.users.mappers.LocalUserMapper;
import com.softparadigm.ProductManagement.users.repositories.LocalUserRepository;
import com.softparadigm.ProductManagement.shoppingcart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalUserService extends AbstractLocalUserService {

    @Autowired
    public LocalUserService(LocalUserRepository userRepository, ShoppingCartService shoppingCartService) {
        super(userRepository, shoppingCartService);
    }


    public LocalUser addLocalUser(UserDTO userDTO) {


        return super.addUser(LocalUserMapper.convertUserDtoToLocalUser(userDTO));
    }


//
//    public LocalUser getUserByEmailAndPassword(String email, String password) {
//        return ((LocalUserRepository) userRepository).findUserByEmailAndPassword(email, password);
//    }

    public LocalUser getUserByEmail(String email) {
        return ((LocalUserRepository) userRepository).findUserByEmail(email);
    }

}
