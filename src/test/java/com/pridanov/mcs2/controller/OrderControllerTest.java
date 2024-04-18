package com.pridanov.mcs2.controller;

import com.pridanov.mcs2.AbstractIntegrationTest;
import com.pridanov.mcs2.TestObjectsFactory;
import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.OrderDetails;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends AbstractIntegrationTest {
    @Test
    void getOrderByDateOk() throws Exception {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Order order = TestObjectsFactory.getOrder();
        OrderDetails orderDetails = order.getOrderDetailsList().get(0);
        order = orderRepository.save(order);
        mockMvc.perform(get("/api/v1/orders/by-price-and-date")
                        .param("date", currentDate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderId", equalTo (order.getOrderId().intValue())))
                .andExpect(jsonPath("$[0].totalCost", equalTo (order.getTotalCost().doubleValue())))
                .andExpect(jsonPath("$[0].dateOfOrder", equalTo (order.getDateOfOrder().toString())))
                .andExpect(jsonPath("$[0].orderDetailsList[0].id", equalTo (orderDetails.getId().intValue())))
                .andExpect(jsonPath("$[0].orderDetailsList[0].productName", equalTo (orderDetails.getProductName())))
                .andExpect(jsonPath("$[0].orderDetailsList[0].count", equalTo (orderDetails.getCount())))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderByDateNotFound() throws Exception {
        String status = "404 NOT_FOUND";
        String message = "No orders for the selected date";
        String tomorrow = LocalDate.now().plusDays(1).toString();
        mockMvc.perform(get("/api/v1/orders/by-price-and-date")
                        .param("date", tomorrow)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", equalTo(status)))
                .andExpect(jsonPath("$.message", equalTo(message)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getOrderByDateWithoutProductOk() throws Exception{
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Order order = TestObjectsFactory.getOrder();
        String productName = "чипсики";
        orderRepository.save(order);
        mockMvc.perform(get("/api/v1/orders/by-date-without-product")
                        .param("dateFrom", currentDate)
                        .param("dateTo", currentDate)
                        .param("productName", productName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderByDateWithoutProductNotFound() throws Exception{
        String status = "404 NOT_FOUND";
        String message = "No orders for the selected time period";
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Order order = TestObjectsFactory.getOrder();
        String productName = "Ежевика";
        orderRepository.save(order);
        mockMvc.perform(get("/api/v1/orders/by-date-without-product")
                        .param("dateFrom", currentDate)
                        .param("dateTo", currentDate)
                        .param("productName", productName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", equalTo(status)))
                .andExpect(jsonPath("$.message", equalTo(message)))
                .andExpect(status().isNotFound());
    }
}
