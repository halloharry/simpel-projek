package com.demo.sistem.transaksi.endpoint;

import com.demo.sistem.transaksi.service.IOrderService;
import com.photo.dto.request.OrderRequestDto;
import com.photo.dto.response.OrderResponseDto;
import com.photo.util.IResultDto;
import com.photo.util.core.APIResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderEndpoint {

    private static final String DEFAULT_SORT_ORDER = "ASC";
    private final IOrderService IOrderService;

    @PostMapping("add-order")
    public IResultDto<OrderResponseDto> addOrder(@RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(IOrderService.inputOrder(orderRequestDto));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @GetMapping("/{user-id}")
    public IResultDto<List<OrderResponseDto>> getOrder(@PathVariable("user-id") Long userId, HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(IOrderService.getOrderByUser(userId));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }

    @GetMapping("/{user-id}/{page}/{size}")
    public IResultDto<List<OrderResponseDto>> getOrderSorting(
            @PathVariable(value = "user-id") Long userId,
            @PathVariable(value = "page", required = false) Integer page,
            @PathVariable(value = "size", required = false) Integer size,
            @RequestParam(name = "sort-order", defaultValue =
                    DEFAULT_SORT_ORDER) String sortOrder,
            HttpServletRequest request) {
        try {
            return APIResponseBuilder.ok(IOrderService.getOrderSorting(userId, page, size, sortOrder));
        } catch (Exception e) {
            return APIResponseBuilder.internalServerError(new ArrayList<>(), e, e.getMessage(), request);
        }
    }
}
