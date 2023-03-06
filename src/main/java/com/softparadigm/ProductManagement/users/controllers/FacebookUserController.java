package com.softparadigm.ProductManagement.users.controllers;

import com.softparadigm.ProductManagement.security.UserDetailsServiceCustom;
import com.softparadigm.ProductManagement.security.jwtAuth.JwtUtils;
import com.softparadigm.ProductManagement.users.entities.FacebookUser;
import com.softparadigm.ProductManagement.users.services.FacebookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/facebook-users")
public class FacebookUserController {


    final private UserDetailsServiceCustom userDetailsServiceCustom;
    final private JwtUtils jwtUtils;

    @Autowired
    public FacebookUserController(FacebookUserService facebookUserService, UserDetailsServiceCustom userDetailsServiceCustom, JwtUtils jwtUtils) {

        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.jwtUtils = jwtUtils;
    }

//
//    @GetMapping("/users-list")
//    public List<FacebookUser> getFacebookUsers() {
//        return userDetailsServiceCustom.getFacebookUserService().getUsers();
//
//    }

    @PostMapping("/authenticate")
    public String authenticateFacebookUser(@RequestBody String accessToken) {
        System.out.println("authentication a facebook user with access token of "+ accessToken);

        try {

            final UserDetails user = userDetailsServiceCustom.loadUserByUsername(accessToken);
            System.out.println("got them "+user.toString());
            return jwtUtils.generateToken(user);
        } catch (Exception e) {
            System.out.println("exception is : " + e.toString());
            return "Error";
        }
    }

}
