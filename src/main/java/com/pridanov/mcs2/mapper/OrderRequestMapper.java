package com.pridanov.mcs2.mapper;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderRequestMapper {
    OrderRequestDto toDto(Order order);

    Order toEntity(OrderRequestDto orderRequestDto);
}
