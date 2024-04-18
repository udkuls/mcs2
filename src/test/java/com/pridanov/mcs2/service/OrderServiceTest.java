package com.pridanov.mcs2.service;

import com.pridanov.mcs2.AbstractIntegrationTest;
import com.pridanov.mcs2.TestObjectsFactory;
import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest extends AbstractIntegrationTest {
    /**
     * тест не будет пройден если микросервис mcs1 для генерации id будет выключен
     */
    @Test
    public void testNewOrder_Success() {
        OrderRequestDto orderRequestDto = TestObjectsFactory.getOrderRequestDto();
        Order order = orderService.newOrder(orderRequestDto);
        assertEquals(orderRepository.findAll().size(), 1);
        assertEquals(orderRequestDto.getDateOfOrder(), order.getDateOfOrder());
        assertEquals(orderRequestDto.getTotalCost(), order.getTotalCost());
        assertEquals(orderRequestDto.getOrderDetailsList().size(), order.getOrderDetailsList().size());
    }
}
