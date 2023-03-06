package com.softparadigm.ProductManagement.shoppingcart;

import com.softparadigm.ProductManagement.security.UserDetailsCustom;
import com.softparadigm.ProductManagement.security.jwtAuth.JwtUtils;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ProductInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/secure/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public ShoppingCartDTO addShoppingCart() {
        return shoppingCartService.addShoppingCart();
    }

    @GetMapping()
    public ShoppingCartDTO getShoppingCartByID() {
        UserDetailsCustom user = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return shoppingCartService.getShoppingCartByID(user.getShoppingCartID());
    }

    @DeleteMapping("/{id}")
    public String deleteShoppingCartByID(@PathVariable String id) {
        shoppingCartService.removeShoppingCartByID(id);
        return "ShoppingCart with ID " + id + " has been successfully deleted ";
    }


    @PutMapping("/addProduct")
    public ShoppingCartDTO addProductToShoppingCart(@RequestParam String productID) {
        UserDetailsCustom user = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return shoppingCartService.addProductToShoppingCart(user.getShoppingCartID(), productID);
    }


    @PutMapping("/incrementProduct")
    public ShoppingCartDTO incrementProductToShoppingCart( @RequestParam String productInfoID) {

        UserDetailsCustom user = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return shoppingCartService.incrementProductToShoppingCart(user.getShoppingCartID(), productInfoID);
    }

    @PutMapping("/decrementProduct")
    public ShoppingCartDTO removeProductFromShoppingCart( @RequestParam String productInfoID) {
        UserDetailsCustom user = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return shoppingCartService.decrementProductFromShoppingCart(user.getShoppingCartID(), productInfoID);
    }


    @PutMapping("/closeShoppingCart/{id}")
    public ShoppingCartDTO closeShoppingCart(@PathVariable String id) {
        return shoppingCartService.closeShoppingCart(id);
    }

    @GetMapping("/closedShoppingCarts")
    public List<ShoppingCartDTO> getClosedShoppingCarts() {
        return shoppingCartService.getClosedShoppingCarts();
    }
}
