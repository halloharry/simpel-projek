package com.demo.sistem.product.mapper;

import com.photo.dto.response.ProductResponseDto;
import com.photo.model.Product;
import com.photo.util.mapper.ADATAMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper extends ADATAMapper<Product, ProductResponseDto> {
    @Override
    public ProductResponseDto convert(Product product) {
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .amount(product.getAmount())
                .kategoriId(product.getKategoriId())
                .build();
    }
}
