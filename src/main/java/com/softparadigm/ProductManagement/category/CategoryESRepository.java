package com.softparadigm.ProductManagement.category;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryESRepository extends ElasticsearchRepository<Category,String> {

    Category findByName(String name) ;
}
