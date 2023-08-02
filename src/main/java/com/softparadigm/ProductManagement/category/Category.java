package com.softparadigm.ProductManagement.category;

import com.softparadigm.ProductManagement.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(indexName = "categories_index")
public class Category {
    @Id
    private String id ;
    private String name ;
    private List<Product> products ;
}
