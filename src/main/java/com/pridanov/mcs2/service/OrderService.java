package com.pridanov.mcs2.service;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;

import java.time.LocalDate;
import java.util.List;



public interface OrderService {
    /**
     * Create new order entity with generated id by external rest api server
     * @param orderRequestDto contain total cost, date of order and list of order details
     * @return order entity
     */
    Order newOrder(OrderRequestDto orderRequestDto);

    /**
     * Get the order with the highest price on a specific date
     * @param date format yyyy-MM-dd
     * @return list of orders
     */
    List<Order> findByPriceAndDate(LocalDate date);

    /**
     * Get a list of order IDs that do not contain a specified item and were received within a given time period
     * @param dateFrom format yyyy-MM-dd
     * @param dateTo format yyyy-MM-dd
     * @param productName name of excluded product
     * @return list of id
     */
    List<Long> findByDateWithoutProduct(LocalDate dateFrom, LocalDate dateTo, String productName);

}
