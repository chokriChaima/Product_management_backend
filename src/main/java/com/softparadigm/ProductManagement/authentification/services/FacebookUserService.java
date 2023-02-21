package com.softparadigm.ProductManagement.authentification.services;

import com.softparadigm.ProductManagement.authentification.models.FacebookUser;
import com.softparadigm.ProductManagement.authentification.repositories.FacebookUserRepository;
import com.softparadigm.ProductManagement.shoppingcart.ShoppingCartService;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacebookUserService {

    private FacebookUserRepository facebookUserRepository;
    private ShoppingCartService shoppingCartService;
    private RestTemplate restTemplate;


    @Autowired
    public FacebookUserService(FacebookUserRepository facebookUserRepository, ShoppingCartService shoppingCartService, RestTemplate restTemplate) {
        this.facebookUserRepository = facebookUserRepository;
        this.shoppingCartService = shoppingCartService;
        this.restTemplate = restTemplate;
    }


    public FacebookUser getFacebookUserByID(String facebookUserID) {
        return facebookUserRepository.findById(facebookUserID).get();
    }

    FacebookUser _convertUserDataToFacebookUser(Map<String, String> data) {

        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setEmail(data.get("email"));
        facebookUser.setId(data.get("id"));
        facebookUser.setName(data.get("name"));
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addShoppingCart();
        facebookUser.setShoppingCartID(shoppingCartDTO.getId());
        return facebookUser;
    }

    // Sign Up Process
    public FacebookUser addFacebookUser(String accessToken) {

        Map<String, String> userData = _getUserData(accessToken);

        FacebookUser facebookUser = _convertUserDataToFacebookUser(userData);
        return facebookUserRepository.save(facebookUser);
    }

    public String authenticateFacebookUser(String accessToken) {
        System.out.println("Authenticate Facebook User with access token = " + accessToken);

        // get user data from the access token with url
        Map<String, String> data = _getUserData(accessToken);

        //query database to find current user
        FacebookUser facebookUser = _getFacebookUserByID(data.get("id"));

        System.out.println("The recognized facebook user is " + facebookUser.toString());
        //generate Json Token that allows THIS facebook User access To THEIR shopping cart

        //return json_token
        return "";

    }

    private Map<String, String> _getUserData(String accessToken) {

        String FACEBOOK_DATA_URL = "https://graph.facebook.com/me";
        String url = FACEBOOK_DATA_URL + "?fields=id,name,email&access_token=" + accessToken;
        return restTemplate.getForObject(url, Map.class);


    }

    public FacebookUser _getFacebookUserByID(String id) {
        return facebookUserRepository.findById(id).get();
    }

//    public String deleteFacebookUser(String accessToken) {
//
//    }
}
