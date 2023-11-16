package com.demo.sistem.transaksi.service.impl;

import com.demo.sistem.transaksi.mapper.OrderResponseMapper;
import com.demo.sistem.transaksi.service.IOrderService;
import com.photo.dao.*;
import com.photo.dto.request.OrderRequestDto;
import com.photo.dto.response.OrderResponseDto;
import com.photo.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IOrderImpl implements IOrderService {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private final IOrderDao IOrderDao;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderResponseMapper orderResponseMapper;

    @Override
    public OrderResponseDto inputOrder(OrderRequestDto orderRequestDto) {
        log.info("order :" + orderRequestDto);
        User user = userRepository.findByIdAndIsDeleted(orderRequestDto.getUserId(), false);
        Product product = productRepository.findByIdAndIsDeleted(orderRequestDto.getProductId(), false);

        if (user == null || product == null) {
            return null;
        } else {
            Order order = new Order();
            order.setUser(user);
            order.setProduct(product);
            order.setAmount(orderRequestDto.getAmount());
            order.setTotalPrice(orderRequestDto.getAmount() * product.getPrice());
            order.setIsCancel(false);
            order.setIsDeleted(false);
            Order savedOrder = IOrderDao.save(order);
            product.setAmount(product.getAmount() - savedOrder.getAmount());
            productRepository.save(product);
            return orderResponseMapper.convert(savedOrder);

        }
    }

    @Override
    public List<OrderResponseDto> getOrderByUser(Long userId) {
        List<Order> orders = IOrderDao.findAllByUserId(userId)
                .stream()
                .filter(x-> !x.getIsDeleted())
                .collect(Collectors.toList());
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }
        return orderResponseMapper.mapEntitiesIntoDTOs(orders);
    }

    @Override
    public List<OrderResponseDto> getOrderSorting(Long userId, Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(
                Optional.ofNullable(page).orElse(1) - 1, Optional.ofNullable(size).orElse(DEFAULT_PAGE_SIZE),
                Sort.by(Sort.Direction.fromString(sort), "id")
        );

        Slice<Order> orders = IOrderDao.findAllOrder(pageable);
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }

        return orderResponseMapper.mapEntitiesIntoDTOs(orders);

    }
}
