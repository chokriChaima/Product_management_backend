package com.softparadigm.ProductManagement.security.jwtAuth;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public JwtAuthFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        System.out.println("Inside JWT auth filter with request "+request.getRequestURI());
        final String authHeader;

        final String jwtToken;


        authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null ) {
            System.out.println("Auth header is null  ");
            filterChain.doFilter(
                    request, response
            );
            return;
        }


        jwtToken = authHeader.substring(7);

        UserDetails userDetails = _getUserDetails(jwtToken);

        if (jwtUtils.validateToken(jwtToken, userDetails)) {
            System.out.println("Token Valid  ");
            _updateSecurityContext(userDetails, request);

        }else {
            System.out.println("Token NOT Valid  ");
        }


        // called to move on to the next filter in the chain
        filterChain.doFilter(request, response);

    }

    private void _updateSecurityContext(UserDetails userDetails, HttpServletRequest request) {
        // username password authentication token object is paramount to updating the security context
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UserDetails _getUserDetails(String jwtToken) {
        //we extract user email from the token
        String userName = jwtUtils.extractUsername(jwtToken);
        return userDetailsService.loadUserByUsername(userName);
    }

}
