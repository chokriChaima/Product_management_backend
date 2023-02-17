package com.softparadigm.ProductManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product newProduct) {
        System.out.println(newProduct);
        return productRepository.save(newProduct);
    }

    public String removeProductByID(String productId) {
        productRepository.deleteById(productId);
        return "Product With id " + productId + "has been removed successfully";
    }

    public Product getProductByID(String productId) {
        return productRepository.findById(productId).get();
    }



}
