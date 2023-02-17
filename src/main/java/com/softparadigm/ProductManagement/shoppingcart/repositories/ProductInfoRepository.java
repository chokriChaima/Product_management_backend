package com.softparadigm.ProductManagement.shoppingcart.repositories;

import com.softparadigm.ProductManagement.shoppingcart.entities.ProductInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductInfoRepository extends MongoRepository<ProductInfo,String> {
}
