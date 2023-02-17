package com.softparadigm.ProductManagement.shoppingcart;

import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ProductInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping
    public List<ShoppingCartDTO> getShoppingCart() {
        return shoppingCartService.getAllShoppingCarts();
    }

    @PostMapping
    public ShoppingCartDTO addShoppingCart() {
        return shoppingCartService.addShoppingCart();
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO getShoppingCartByID(@PathVariable String id) {
        return shoppingCartService.getShoppingCartByID(id);
    }



    @DeleteMapping("/{id}")
    public String deleteShoppingCartByID(@PathVariable String id) {
        shoppingCartService.removeShoppingCartByID(id);
        return "ShoppingCart with ID " + id + " has been successfully deleted ";
    }


    @PutMapping("/addProduct")
    public ShoppingCartDTO addProductToShoppingCart(@RequestParam String cartID, @RequestParam String productID ) {
        return shoppingCartService.addProductToShoppingCart(cartID, productID);
    }


    @PutMapping("/incrementProduct")
    public ShoppingCartDTO incrementProductToShoppingCart(@RequestParam String cartID, @RequestParam String productInfoID) {
        return shoppingCartService.incrementProductToShoppingCart(cartID, productInfoID);
    }

    @PutMapping("/decrementProduct")
    public ShoppingCartDTO removeProductFromShoppingCart(@RequestParam String cartID, @RequestParam String productInfoID) {
        return shoppingCartService.decrementProductFromShoppingCart(cartID, productInfoID);
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
