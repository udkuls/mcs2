package com.pridanov.mcs2.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for submitting an order request")
public class OrderRequestDto {
    @Schema(description = "Total cost of the order")
    private BigDecimal totalCost;
    @Schema(description = "Date of the order")
    private LocalDate dateOfOrder;
    @Schema(description = "List of order details for the order")
    private List<OrderDetailsRequestDto> orderDetailsList = new ArrayList<>();
}
