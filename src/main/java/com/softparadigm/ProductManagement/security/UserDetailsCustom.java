package com.softparadigm.ProductManagement.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserDetailsCustom implements UserDetails {

    @Override
    public String toString() {
        return "UserDetailsCustom{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", shoppingCartID='" + shoppingCartID + '\'' +
                '}';
    }

    private String userId;
    private String email;
    private String password;
    private String shoppingCartID;

    public UserDetailsCustom(String userId, String email, String password, String shoppingCartID) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.shoppingCartID = shoppingCartID;
    }

    public String getUserId() {
        return userId;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
