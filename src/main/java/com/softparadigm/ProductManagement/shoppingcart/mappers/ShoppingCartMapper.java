package com.softparadigm.ProductManagement.shoppingcart.mappers;

import com.softparadigm.ProductManagement.shoppingcart.dtos.ProductInfoDTO;
import com.softparadigm.ProductManagement.shoppingcart.dtos.ShoppingCartDTO;
import com.softparadigm.ProductManagement.shoppingcart.entities.ProductInfo;
import com.softparadigm.ProductManagement.shoppingcart.entities.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartMapper {
    public static ShoppingCartDTO convertEntityToDTO(ShoppingCart entity) {


        List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();


        for (ProductInfo productInfo : entity.getProductInfoList()) {

            productInfoDTOList.add(ProductInfoMapper.convertEntityToDTO(productInfo));
        }

        return new ShoppingCartDTO(entity.getId(), productInfoDTOList, calculateCartTotalPrice(productInfoDTOList), calculateCartTotalQuantity(productInfoDTOList), entity.getPaid()

        );
    }

    public static ShoppingCart convertDTOtoEntity(ShoppingCartDTO dto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getId());
        shoppingCart.setProductInfoList(
                dto.getproductInfoDTOList()
                        .stream()
                        .map((e) -> ProductInfoMapper.convertDTOtoEntity(e))
                        .collect(Collectors.toList())
        );
        shoppingCart.setPaid(dto.getPaid());
        return shoppingCart;

    }


    static double calculateCartTotalPrice(List<ProductInfoDTO> productInfoDTOList) {
        double totalPrice = 0;

        for (ProductInfoDTO productInfoDTO : productInfoDTOList) {

            totalPrice += productInfoDTO.getTotalPrice();
        }
        return totalPrice;

    }

    static int calculateCartTotalQuantity(List<ProductInfoDTO> productInfoDTOList) {

        int totalQuantity = 0;

        for (ProductInfoDTO productInfoDTO : productInfoDTOList) {

            totalQuantity += productInfoDTO.getQuantity();
        }
        return totalQuantity;

    }

}
