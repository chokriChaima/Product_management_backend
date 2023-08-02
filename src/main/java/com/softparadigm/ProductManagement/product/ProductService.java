package com.softparadigm.ProductManagement.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductESRepository productESRepository;


    public ProductService(ProductESRepository productESRepository) {
        this.productESRepository = productESRepository;
    }

    public SearchHits<Product> searchProductsByName(String name) {

        return productESRepository.findByProductName(name);
    }

    public SearchHits<Product> searchProductsByNameOrExactPrice(String name, double exactPrice) {
        return productESRepository.findByProductNameOrProductPrice(name, exactPrice);
    }

    public SearchHits<Product> searchProductsByNameAndExactPrice(String name, double exactPrice) {
        return productESRepository.findByProductNameAndProductPrice(name, exactPrice);
    }

    public Page<Product> getProducts(int pageNumber) {

        return productESRepository.findAll(PageRequest.of(pageNumber, 5));
    }

    public Product addProduct(Product newProduct) {
        System.out.println(newProduct);
        return productESRepository.save(newProduct);
    }

    public List<Product> addMultipleProducts(List<Product> newProducts) {
        System.out.println(newProducts);
        return (List<Product>) productESRepository.saveAll(newProducts);

    }

    public String removeProductByID(String productId) {
        productESRepository.deleteById(productId);
        return "Product With id " + productId + "has been removed successfully";
    }


    public SearchHits<Product> searchProductsByNameAndRangedPrice(String name, String rangedPrice) {


        int dividerIndex = rangedPrice.indexOf('-');
        double minValue = Double.parseDouble(rangedPrice.substring(0, dividerIndex));
        double maxValue = Double.parseDouble(rangedPrice.substring(dividerIndex + 1));
        return productESRepository.findByProductPriceBetweenAndProductName(minValue, maxValue, name);
    }

    public List<Product> searchByProductNameAllowFuzziness(String name) {
        return productESRepository.findByProductNameAllowFuzziness(name);
    }

    public List<String> autocompleteProductName(String name) {
        return productESRepository.findByProductNameStartingWith(name).stream().map(Product::getProductName).toList();
    }

}
