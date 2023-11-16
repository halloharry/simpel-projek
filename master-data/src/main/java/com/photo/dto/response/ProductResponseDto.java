package com.photo.dto.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductResponseDto {
    private Long kategoriId;
    private String productName;
    private Double price;
    private Integer amount;
}
