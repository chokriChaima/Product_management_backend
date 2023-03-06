package com.softparadigm.ProductManagement.users.controllers;

import com.softparadigm.ProductManagement.users.dtos.UserDTO;
import com.softparadigm.ProductManagement.users.services.LocalUserService;
import com.softparadigm.ProductManagement.users.entities.LocalUser;
import com.softparadigm.ProductManagement.security.UserDetailsServiceCustom;
import com.softparadigm.ProductManagement.security.jwtAuth.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/local-users")
public class LocalUserController {

    final LocalUserService userService;
    final private UserDetailsServiceCustom userDetailsServiceCustom;
    final private JwtUtils jwtUtils;

    @Autowired
    public LocalUserController(LocalUserService userService,UserDetailsServiceCustom userDetailsServiceCustom, JwtUtils jwtUtils) {
        this.userService = userService;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/users-list")
    public List<LocalUser> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public LocalUser addUser(@RequestBody UserDTO user) {
        return userService.addLocalUser(user);
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@PathVariable String userID) {
        return userService.deleteUser(userID);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserDTO body) {

        System.out.println("authentication a local user"+ body.toString());

        try {
            System.out.println("getting user from dbase");
            final UserDetails user = userDetailsServiceCustom.loadUserByUsername(body.getEmail());
            System.out.println("got them "+user.toString());
            return
                    jwtUtils.generateToken(user);
        } catch (Exception e) {
            System.out.println("exception is : " + e.toString());
            return "Error";
        }

    }

}
