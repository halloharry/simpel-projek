package com.demo.sistem.product.endpoint;

import com.demo.sistem.product.service.product.ProductService;
import com.photo.dto.request.ProductRequestDto;
import com.photo.dto.response.ProductResponseDto;
import com.photo.util.IResultDto;
import com.photo.util.core.APIResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductEndpoint {

    private static final String DEFAULT_SORT_ORDER = "ASC";
    private final ProductService productService;

    @PostMapping("/add-product")
    public IResultDto<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(productService.addProduct(productRequestDto));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @GetMapping("/items/{page}/{size}")
    public IResultDto<List<ProductResponseDto>> getProduct(@PathVariable(value = "page", required = false) Integer page,
                                                           @PathVariable(value = "size", required = false) Integer size,
                                                           @RequestParam(value = "q", required = false) String keywords,
                                                           @RequestParam(name = "sort-order", defaultValue =
                                                                   DEFAULT_SORT_ORDER) String sortOrder,
                                                           HttpServletRequest request) {
        try {
            List<ProductResponseDto> responseDtos = productService.getProductSorted(page, size, keywords, sortOrder);
            if (Objects.isNull(responseDtos)) {
                return APIResponseBuilder.noContent(Collections.emptyList());
            }
            return APIResponseBuilder.ok(responseDtos);
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }
}

