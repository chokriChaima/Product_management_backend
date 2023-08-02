package com.softparadigm.ProductManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
//@EnableElasticsearchRepositories(basePackages = "com.softparadigm.ProductManagement.product")
public class ProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}

}
