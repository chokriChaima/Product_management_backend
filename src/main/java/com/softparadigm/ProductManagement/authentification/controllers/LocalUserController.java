package com.softparadigm.ProductManagement.authentification.controllers;

import com.softparadigm.ProductManagement.authentification.services.LocalUserService;
import com.softparadigm.ProductManagement.authentification.models.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/local-users")
public class LocalUserController {

    final LocalUserService userService;


    @Autowired
    public LocalUserController(LocalUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<LocalUser> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public LocalUser addUser(@RequestBody LocalUser user) {
        return userService.addUser(user);
    }

    @DeleteMapping
    public String deleteUser(@PathVariable String userID) {
        return userService.deleteUser(userID);
    }

    @GetMapping("/authenticate")
    public LocalUser getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return userService.getUserByEmailAndPassword(email, password);

    }

}
