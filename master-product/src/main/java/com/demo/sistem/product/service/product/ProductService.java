package com.demo.sistem.product.service.product;

import com.photo.dto.request.ProductRequestDto;
import com.photo.dto.response.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    List<ProductResponseDto> getProductSorted(Integer page, Integer size, String keywords, String sort);
    ProductResponseDto addProduct(ProductRequestDto productRequest);
}
