package com.softparadigm.ProductManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }


    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        return productService.addProduct(newProduct);
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable String id) {
        return productService.getProductByID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProductByID(@PathVariable String id) {
        productService.removeProductByID(id);
        return "Product with ID " + id + " has been successfully deleted ";
    }

}
