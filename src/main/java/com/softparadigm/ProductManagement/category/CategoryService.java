package com.softparadigm.ProductManagement.category;

import com.softparadigm.ProductManagement.product.Product;
import com.softparadigm.ProductManagement.product.ProductESRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {

    final private CategoryESRepository categoryRepository;
    final private ProductESRepository productRepository;

    public CategoryService(CategoryESRepository repository, ProductESRepository productRepository) {
        this.categoryRepository = repository;
        this.productRepository = productRepository;
    }

    public Page<Category> getCategories(int pageNumber) {
        return categoryRepository.findAll(PageRequest.of(pageNumber, 7));
    }
    public Page<Category> getCategoriesByPageWillSorting(int number) {
        return categoryRepository.findAll(PageRequest.of(number, 7));

    }
    public Category addCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }


    public boolean addMultipleProductsToCategory(List<String> productIds, String categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        List<Product> products = StreamSupport.stream(productRepository.findAllById(productIds).spliterator(), false).toList();


        if (optionalCategory.isPresent() && !products.isEmpty()) {

            Category category = optionalCategory.get();
            if (category.getProducts() == null) {
                category.setProducts(new ArrayList<>());
            }
            category.getProducts().addAll(products);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    public void removeCategoryByID(String id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> addMultipleCategories(List<Category> newCategories) {
        return StreamSupport
                .stream(
                        categoryRepository
                                .saveAll(newCategories)
                                .spliterator(), false)
                .toList();
    }


    public List<Product> getProductsByCategory(String name) {
       Category category = categoryRepository.findByName(name);
       return category.getProducts();


    }


}
