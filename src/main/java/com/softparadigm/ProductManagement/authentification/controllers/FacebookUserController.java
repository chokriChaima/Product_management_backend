package com.softparadigm.ProductManagement.authentification.controllers;

import com.softparadigm.ProductManagement.authentification.services.FacebookUserService;
import com.softparadigm.ProductManagement.authentification.services.FacebookUserService;
import com.softparadigm.ProductManagement.authentification.models.FacebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/facebook-users")
public class FacebookUserController {

    private FacebookUserService facebookUserService;


    @Autowired
    public FacebookUserController(FacebookUserService facebookUserService) {
        this.facebookUserService = facebookUserService;
    }



    @PostMapping("/new-user")
    public FacebookUser addUser(@RequestBody FacebookUser user) {
        return facebookUserService.addFacebookUser(user);
    }

//    @DeleteMapping
//    public String deleteUser(@RequestBody String accessToken) {
//        return facebookUserService.deleteFacebookUser(userID);
//    }

    @PostMapping("/authentication")
    public String authenticateFacebookUser(@RequestBody String accessToken) {
        return facebookUserService.authenticateFacebookUser(accessToken);
    }

}
