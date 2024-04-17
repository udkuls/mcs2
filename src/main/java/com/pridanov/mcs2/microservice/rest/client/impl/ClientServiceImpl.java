package com.pridanov.mcs2.microservice.rest.client.impl;

import com.pridanov.mcs2.config.properties.RestClientProperties;
import com.pridanov.mcs2.entity.dto.OrderNumberDto;
import com.pridanov.mcs2.microservice.rest.client.ClientService;
import com.pridanov.mcs2.microservice.rest.client.RestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final RestClient restClient;
    private final RestClientProperties restClientProperties;

    @Override
    public OrderNumberDto GetOrderNumber() {
        String host = restClientProperties.getHost();
        String port = restClientProperties.getPort();
        String url = String.format("http://%s:%s/api/v1/order-numbers", host, port);
        return restClient.getForObject(url, OrderNumberDto.class);
    }
}
