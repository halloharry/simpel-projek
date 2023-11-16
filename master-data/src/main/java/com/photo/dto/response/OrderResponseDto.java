package com.photo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {
    private Long userId;
    private Long productId;
    private Long price;
    private Double totalPrice;
    private Integer orderAmount;
    private Boolean isCancel;
    private Integer stock;
}
