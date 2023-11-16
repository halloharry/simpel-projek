package com.photo.dto.request;

import lombok.Data;

@Data
public class OrderRequestDto {
    private Long userId;
    private Long productId;
    private Long price;
    private Long totalPrice;
    private Integer amount;
    private Integer isCancel;
}
