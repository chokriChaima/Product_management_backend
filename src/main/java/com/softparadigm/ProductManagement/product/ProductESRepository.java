package com.softparadigm.ProductManagement.product;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ProductESRepository extends ElasticsearchRepository<Product, String> {
    SearchHits<Product> findByProductName(String name);
    SearchHits<Product> findByProductNameOrProductPrice(String name,double exactPrice);
    SearchHits<Product> findByProductNameAndProductPrice(String name,double exactPrice);
    SearchHits<Product> findByProductPriceBetweenAndProductName(double minValue, double maxValue,String name);
    @Query("{\"multi_match\" : {\"query\" : \"?0\", \"fields\": [\"productName\"], \"fuzziness\" :\"AUTO\"}}")
    List<Product> findByProductNameAllowFuzziness(String name);
//    List<Product> findByProductNameLike(String name);

    List<Product> findByProductNameStartingWith(String name);
}
