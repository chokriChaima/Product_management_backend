package com.softparadigm.ProductManagement.product;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/strict-search")
    public List<SearchHit<Product>> searchProducts(@RequestParam String name, @RequestParam(required = false) Double exactPrice, @RequestParam(required = false) String rangedPrice) {

        if (exactPrice != null) {

            return productService.searchProductsByNameAndExactPrice(name, exactPrice.doubleValue()).getSearchHits();
        } else if (rangedPrice != null) {
            return productService.searchProductsByNameAndRangedPrice(name, rangedPrice).getSearchHits();

        } else {
            return productService.searchProductsByName(name).getSearchHits();

        }
    }

    @GetMapping("fuzzy-search")
    public List<Product> searchProductsByKeywordAllowFuzziness(@RequestParam String name){
      return   productService.searchByProductNameAllowFuzziness(name);

    }

    @GetMapping("autocomplete")
    public List<String> autocompleteProductName(@RequestParam String name){
      return   productService.autocompleteProductName(name);

    }

//    @GetMapping("/simple-search")
//    public List<Product> searchProductsByName(@RequestParam String name) {
//        return productService.searchProductsByName(name);
//    }

    @GetMapping("/page/{pageNumber}")
    public Page<Product> getProductsByPage(@PathVariable int pageNumber) {
        return productService.getProducts(pageNumber);
    }
    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        return productService.addProduct(newProduct);
    }

    @PostMapping("/multiple")
    public List<Product> addMultipleProducts(@RequestBody List<Product> newProducts) {
        return productService.addMultipleProducts(newProducts);
    }


    @DeleteMapping("/{id}")
    public String deleteProductByID(@PathVariable String id) {
        productService.removeProductByID(id);
        return "Product with ID " + id + " has been successfully deleted ";
    }

}
