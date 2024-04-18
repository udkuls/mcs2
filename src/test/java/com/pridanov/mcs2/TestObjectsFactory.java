package com.pridanov.mcs2;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.OrderDetails;
import com.pridanov.mcs2.entity.dto.OrderDetailsRequestDto;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public abstract class TestObjectsFactory {
    public static Order getOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setTotalCost(new BigDecimal("10.22"));
        order.setDateOfOrder(LocalDate.now());
        order.setOrderDetailsList(getOrderDetailsList(order));
        return order;
    }

    public static OrderRequestDto getOrderRequestDto(){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setTotalCost(new BigDecimal("10.22"));
        orderRequestDto.setDateOfOrder(LocalDate.now());
        orderRequestDto.setOrderDetailsList(getOrderDetailsListDto());
        return orderRequestDto;
    }

    private static List<OrderDetailsRequestDto> getOrderDetailsListDto(){
        OrderDetailsRequestDto orderDetailsRequestDto = new OrderDetailsRequestDto();
        orderDetailsRequestDto.setCount(1000);
        orderDetailsRequestDto.setProductName("Вишня");
        return List.of(orderDetailsRequestDto);
    }

    private static List<OrderDetails> getOrderDetailsList(Order order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(1L);
        orderDetails.setOrder(order);
        orderDetails.setCount(2000);
        orderDetails.setProductName("Ежевика");
        return List.of(orderDetails);
    }
}
