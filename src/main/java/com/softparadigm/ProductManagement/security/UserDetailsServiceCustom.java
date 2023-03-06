package com.softparadigm.ProductManagement.security;

import com.softparadigm.ProductManagement.users.entities.LocalUser;
import com.softparadigm.ProductManagement.users.services.FacebookUserService;
import com.softparadigm.ProductManagement.users.services.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service

public class UserDetailsServiceCustom implements UserDetailsService {
    public LocalUserService getLocalUserService() {
        return localUserService;
    }

    public FacebookUserService getFacebookUserService() {
        return facebookUserService;
    }

    private final LocalUserService localUserService;
    private final FacebookUserService facebookUserService;

    //
    @Autowired
    public UserDetailsServiceCustom(LocalUserService localUserService

            , FacebookUserService facebookUserService
    ) {
        this.localUserService = localUserService;
        this.facebookUserService = facebookUserService;
    }

    public boolean isEmail(String emailOrAccessToken) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);
        // Match the email with the regular expression
        Matcher matcher = pattern.matcher(emailOrAccessToken);
        // Return true if the email matches the pattern, false otherwise
        return matcher.matches();

    }

    @Override
    public UserDetails loadUserByUsername(String emailOrAccessToken) throws UsernameNotFoundException {


        LocalUser dbUser = null;

        dbUser = localUserService.getUserByEmail(emailOrAccessToken);
        if (dbUser == null) {


            // algorithm should be better should check for patterns in an email
            if (isEmail(emailOrAccessToken)) {
                dbUser = facebookUserService.getUserByEmail(emailOrAccessToken);
            } else {
                dbUser = facebookUserService.authenticateFacebookUser(emailOrAccessToken);

            }

        }

        if (dbUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsCustom(
                dbUser.getUserId(),
                dbUser.getEmail(),
                dbUser.getPassword(),
                dbUser.getShoppingCartID());

    }
}


