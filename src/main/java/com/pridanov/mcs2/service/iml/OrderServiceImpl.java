package com.pridanov.mcs2.service.iml;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.OrderDetails;
import com.pridanov.mcs2.entity.dto.OrderNumberDto;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;
import com.pridanov.mcs2.mapper.OrderRequestMapper;
import com.pridanov.mcs2.microservice.rest.client.ClientService;
import com.pridanov.mcs2.repository.OrderRepository;
import com.pridanov.mcs2.service.OrderService;
import com.pridanov.mcs2.util.exceptions.NotFoundException;
import com.pridanov.mcs2.util.exceptions.UniqueConstraintException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final OrderRequestMapper orderRequestMapper;



    @Transactional
    @Override
    public Order newOrder(OrderRequestDto orderRequestDto) {
        Order order = orderRequestMapper.toEntity(orderRequestDto);
        OrderNumberDto orderNumberDto = clientService.GetOrderNumber();
        if(orderRepository.findById(orderNumberDto.getOrderNumber()).isPresent()) {
            throw new UniqueConstraintException("An order with this ID already exists, try again");
        }
        order.setOrderId(orderNumberDto.getOrderNumber());
        order.setDateOfOrder(LocalDate.from(orderNumberDto.getOrderDate()));
        long id = 0L;
        for (OrderDetails orderDetails : order.getOrderDetailsList()) {
            orderDetails.setOrder(order);
            orderDetails.setId(++id);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByPriceAndDate(LocalDate date) {
        List<Order> order = orderRepository.findByPriceAndDate(date);
        if(order.isEmpty()) throw new NotFoundException("No orders for the selected date");
        return order;

    }

    @Override
    public List<Long> findByDateWithoutProduct(LocalDate dateFrom, LocalDate dateTo, String productName) {
        List<Long> list = orderRepository.findByDateWithoutProduct(dateFrom, dateTo, productName);
        if(list.isEmpty()) throw new NotFoundException("No orders for the selected time period");
        return list;
    }


}


