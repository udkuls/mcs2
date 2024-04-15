package com.pridanov.mcs2.service.iml;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.OrderDetails;
import com.pridanov.mcs2.entity.dto.OrderNumberDto;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;
import com.pridanov.mcs2.mapper.OrderRequestMapper;
import com.pridanov.mcs2.microservice.rest.client.ClientService;
import com.pridanov.mcs2.repository.OrderRepository;
import com.pridanov.mcs2.service.OrderService;
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
        System.out.println("sdsdsdsd");
        Order order = orderRequestMapper.toEntity(orderRequestDto);
        OrderNumberDto orderNumberDto = clientService.GetOrderNumber();
        System.out.println(orderNumberDto);
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
        return orderRepository.findByPriceAndDate(date);
    }

    @Override
    public List<Long> findByDateWithoutProduct(LocalDate dateFrom, LocalDate dateTo, String productName) {
        return orderRepository.findByDateWithoutProduct(dateFrom, dateTo, productName);
    }


}


