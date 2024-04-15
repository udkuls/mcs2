package com.pridanov.mcs2.microservice.rest.client.impl;

import com.pridanov.mcs2.entity.dto.OrderNumberDto;
import com.pridanov.mcs2.microservice.rest.client.ClientService;
import com.pridanov.mcs2.microservice.rest.client.RestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final RestClient restClient;

    @Override
    public OrderNumberDto GetOrderNumber() {
        String url = "http://localhost:8081/api/v1/order-numbers";
        return restClient.getForObject(url, OrderNumberDto.class);
    }
}
