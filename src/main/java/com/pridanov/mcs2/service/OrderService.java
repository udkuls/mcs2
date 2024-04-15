package com.pridanov.mcs2.service;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;

import java.time.LocalDate;
import java.util.List;



public interface OrderService {
    Order newOrder(OrderRequestDto orderRequestDto);
    List<Order> findByPriceAndDate(LocalDate totalCost);
    List<Long> findByDateWithoutProduct(LocalDate dateFrom, LocalDate dateTo, String productName);

}
