package com.demo.sistem.transaksi.mapper;

import com.photo.dto.response.OrderResponseDto;
import com.photo.model.Order;
import com.photo.util.mapper.ADATAMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper extends ADATAMapper<Order, OrderResponseDto> {
    @Override
    public OrderResponseDto convert(Order order) {
        return OrderResponseDto.builder()
                .userId(order.getUser().getId())
                .productId(order.getProduct().getId())
                .orderAmount(order.getAmount())
                .totalPrice(order.getTotalPrice())
                .isCancel(order.getIsCancel())
                .build();
    }
}
