package com.softparadigm.ProductManagement.shoppingcart;

import com.softparadigm.ProductManagement.product.Product;
import com.softparadigm.ProductManagement.product.ProductRepository;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import com.softparadigm.ProductManagement.shoppingcart.entities.ProductInfo;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ProductInfoDTO;
import com.softparadigm.ProductManagement.shoppingcart.entities.ShoppingCart;
import com.softparadigm.ProductManagement.shoppingcart.mappers.ProductInfoMapper;
import com.softparadigm.ProductManagement.shoppingcart.mappers.ShoppingCartMapper;
import com.softparadigm.ProductManagement.shoppingcart.repositories.ProductInfoRepository;
import com.softparadigm.ProductManagement.shoppingcart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductRepository productRepository;


    public List<ShoppingCartDTO> getAllShoppingCarts() {
        return shoppingCartRepository.findAll().stream().map((e) -> ShoppingCartMapper.convertEntityToDTO(e)).collect(Collectors.toList());
    }

    public ShoppingCartDTO getShoppingCartByID(String id) {
        return ShoppingCartMapper.convertEntityToDTO(shoppingCartRepository.findById(id).get());
    }

    public ShoppingCartDTO addShoppingCart() {
        ShoppingCart entity = shoppingCartRepository.save(new ShoppingCart());
        return ShoppingCartMapper.convertEntityToDTO(entity);

    }

    public String removeShoppingCartByID(String productId) {
        shoppingCartRepository.deleteById(productId);
        return "Shopping Cart With id " + productId + "has been removed successfully";
    }

    public ShoppingCartDTO addProductToShoppingCart(String cartID, String productID) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartID).get();

        Product product = productRepository.findById(productID).get();

        ProductInfo productInfo = new ProductInfo();

        productInfo.setProduct(product);
        productInfo.setQuantity(1);

        shoppingCart.getProductInfoList().add(productInfoRepository.save(productInfo));
        return ShoppingCartMapper.convertEntityToDTO(shoppingCartRepository.save(shoppingCart));

    }

    public ShoppingCartDTO closeShoppingCart(String id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).get();
        shoppingCart.setPaid(true);
        return ShoppingCartMapper.convertEntityToDTO(shoppingCartRepository.save(shoppingCart));

    }

    public List<ShoppingCartDTO> getClosedShoppingCarts() {
        return shoppingCartRepository.findPaidShoppingCart(true).stream().map(e -> ShoppingCartMapper.convertEntityToDTO(e)).collect(Collectors.toList());
    }

    public ShoppingCartDTO incrementProductToShoppingCart(String cartID, String productInfoID) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartID).get();
        ProductInfo productInfo = productInfoRepository.findById(productInfoID).get();

        int indexOf = shoppingCart.getProductInfoList().indexOf(productInfo);
        shoppingCart.getProductInfoList().remove(indexOf);
        productInfo.setQuantity(productInfo.getQuantity() + 1);

        shoppingCart.getProductInfoList().add(indexOf,productInfoRepository.save(productInfo));
        return ShoppingCartMapper.convertEntityToDTO(shoppingCartRepository.save(shoppingCart));


    }

    public ShoppingCartDTO decrementProductFromShoppingCart(String cartID, String productInfoID) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartID).get();

        ProductInfo productInfo = productInfoRepository.findById(productInfoID).get();
        int indexOf = shoppingCart.getProductInfoList().indexOf(productInfo);
        shoppingCart.getProductInfoList().remove(indexOf);


        if (productInfo.getQuantity() == 1) {
            productInfoRepository.deleteById(productInfo.getId());

        } else {
            productInfo.setQuantity(productInfo.getQuantity() - 1);
            shoppingCart.getProductInfoList().add(indexOf,productInfoRepository.save(productInfo));
        }

        return ShoppingCartMapper.convertEntityToDTO(shoppingCartRepository.save(shoppingCart));
    }
}
