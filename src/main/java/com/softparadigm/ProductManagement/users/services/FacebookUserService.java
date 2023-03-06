package com.softparadigm.ProductManagement.users.services;

import com.softparadigm.ProductManagement.shoppingcart.ShoppingCartService;
import com.softparadigm.ProductManagement.users.entities.FacebookUser;
import com.softparadigm.ProductManagement.users.repositories.FacebookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FacebookUserService extends AbstractLocalUserService {


    private final RestTemplate restTemplate;

    @Autowired
    public FacebookUserService(FacebookUserRepository userRepository, ShoppingCartService shoppingCartService, RestTemplate restTemplate) {
        super(userRepository, shoppingCartService);
        this.restTemplate = restTemplate;
    }


    FacebookUser _convertUserDataToFacebookUser(Map<String, String> data) {

        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setEmail(data.get("email"));
        facebookUser.setFacebookId(data.get("id"));
        facebookUser.setName(data.get("name"));
        facebookUser.generateAndSetPassword();
        return facebookUser;
    }

    // Sign Up Process
    public FacebookUser addFacebookUser(Map<String, String> userData) {
        FacebookUser facebookUser = _convertUserDataToFacebookUser(userData);
        return (FacebookUser) super.addUser(facebookUser);
    }

    public FacebookUser authenticateFacebookUser(String accessToken) {


        Map<String, String> data = _getUserDataFromGraphAPI(accessToken);

        //query database to find current user

        FacebookUser facebookUser = ((FacebookUserRepository) super.userRepository).findUserByFacebookId(data.get("id"));

        if (facebookUser == null) {

            return addFacebookUser(data);
        }
        return facebookUser;

    }

    private Map<String, String> _getUserDataFromGraphAPI(String accessToken) {

        String FACEBOOK_DATA_URL = "https://graph.facebook.com/me";
        String url = FACEBOOK_DATA_URL + "?fields=id,name,email&access_token=" + accessToken;

        return restTemplate.getForObject(url, Map.class);


    }


    public FacebookUser getUserByEmail(String email) {
        return ((FacebookUserRepository) super.userRepository).findUserByEmail(email);

    }
}



