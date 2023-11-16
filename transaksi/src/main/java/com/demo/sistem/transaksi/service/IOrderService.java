package com.demo.sistem.transaksi.service;


import com.photo.dto.request.OrderRequestDto;
import com.photo.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderService {
    OrderResponseDto inputOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getOrderByUser(Long userId);
    List<OrderResponseDto> getOrderSorting(Long userId, Integer page, Integer size, String sort);
}
