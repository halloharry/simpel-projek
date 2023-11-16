package com.photo.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {
    private Long kategoriId;
    private String productName;
    private Double price;
    private Integer amount;
}
