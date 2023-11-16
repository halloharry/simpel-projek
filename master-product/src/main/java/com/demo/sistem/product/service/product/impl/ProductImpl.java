package com.demo.sistem.product.service.product.impl;

import com.demo.sistem.product.mapper.ProductResponseMapper;
import com.demo.sistem.product.service.product.ProductService;
import com.photo.dao.ProductRepository;
import com.photo.dto.request.ProductRequestDto;
import com.photo.dto.response.ProductResponseDto;
import com.photo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductImpl implements ProductService {
    public static final int DEFAULT_PAGE = 10;
    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;


    @Override
    public List<ProductResponseDto> getProductSorted(Integer page, Integer size, String keywords, String sort) {

        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(1) - 1, Optional.ofNullable(size).orElse(DEFAULT_PAGE),
                Sort.by(Sort.Direction.fromString(sort), "id")
        );
        Slice<Product> products = productRepository.findAllProduct(keywords, pageable);
        if (products.isEmpty()) {
            return Collections.emptyList();
        }
        return productResponseMapper.mapEntitiesIntoDTOs(products.getContent().stream().filter(x-> !x.getIsDeleted()).collect(Collectors.toList()));
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequest) {
        Product product = new Product();

        product.setKategoriId(productRequest.getKategoriId());
        product.setProductName(productRequest.getProductName());
        product.setAmount(productRequest.getAmount());
        product.setPrice(productRequest.getPrice());
        product.setIsDeleted(false);
        productRepository.save(product);
        return productResponseMapper.convert(product);
    }
}
