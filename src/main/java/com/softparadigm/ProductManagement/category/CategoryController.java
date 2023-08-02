package com.softparadigm.ProductManagement.category;

import com.softparadigm.ProductManagement.product.Product;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/page/{number}")
    public List<String> getCategoriesByPage(@PathVariable int number) {
        return categoryService.getCategories(number).stream().map((Category::getName)).toList();

    }

    @GetMapping("/page-with-sorting/{number}")
    public List<String> getCategoriesByPageWillSorting(@PathVariable int number) {
        return categoryService.getCategoriesByPageWillSorting(number).stream().map((Category::getName)).toList();

    }
    @GetMapping("/{name}")
    public List<Product> getProductsByCategory(@PathVariable String name) {
        return categoryService.getProductsByCategory(name);

    }

    @PostMapping
    public Category addCategory(@RequestBody Category newCategory) {
        return categoryService.addCategory(newCategory);
    }
    @PostMapping("/multiple")
    public List<Category> addMultipleCategories(@RequestBody List<Category> newCategories) {
        return categoryService.addMultipleCategories(newCategories);
    }

    @PostMapping("/multiple/products")
    public boolean addMultipleProductsToCategory(@RequestBody Map<String, Object> data) {
        List<String> productIds = (List<String>) data.get("productsId");
        String categoryId = data.get("categoryId").toString();
        return categoryService.addMultipleProductsToCategory(productIds, categoryId);
    }


    @DeleteMapping("/{id}")
    public String deleteCategoryByID(@PathVariable String id) {
        categoryService.removeCategoryByID(id);
        return "Category with ID " + id + " has been successfully deleted ";
    }
}
