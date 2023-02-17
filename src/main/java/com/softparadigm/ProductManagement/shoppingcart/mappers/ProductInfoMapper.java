package com.softparadigm.ProductManagement.shoppingcart.mappers;

import com.softparadigm.ProductManagement.product.Product;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ProductInfoDTO;
import com.softparadigm.ProductManagement.shoppingcart.entities.ProductInfo;

public class ProductInfoMapper {
    static ProductInfoDTO convertEntityToDTO(ProductInfo entity) {

        ProductInfoDTO dto = new ProductInfoDTO();

        dto.setProductInfoID(entity.getId());
        dto.setProductID(entity.getProduct().getProductID());
        dto.setProductName(entity.getProduct().getProductName());
        dto.setQuantity(entity.getQuantity());
        dto.setProductPrice(entity.getProduct().getProductPrice());
        dto.setTotalPrice(entity.getProduct().getProductPrice() * entity.getQuantity());

        System.out.println("Converting Product Info entity to DTO " + dto);
        return dto;
    }

    public static ProductInfo convertDTOtoEntity(ProductInfoDTO dto) {
        Product product = new Product();

        product.setProductID(dto.getProductID());
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());

        ProductInfo productInfo = new ProductInfo();
        productInfo
                .setProduct(product);
        productInfo.setId(dto.getProductInfoID());
        productInfo.setQuantity(dto.getQuantity());
        System.out.println("Converting Product Info DTO to Entity " + productInfo);

        return productInfo;
    }
}
