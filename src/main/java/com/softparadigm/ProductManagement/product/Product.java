package com.softparadigm.ProductManagement.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

@Setting(settingPath = "/erp-product.json")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Document(indexName = "products_index") // For Elasticsearch
public class Product {
    @Id
    private String productID;
    private String productName;
    private double productPrice;

}
