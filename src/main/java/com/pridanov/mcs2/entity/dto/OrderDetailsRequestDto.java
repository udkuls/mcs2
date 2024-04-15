package com.pridanov.mcs2.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for requesting order details")
public class OrderDetailsRequestDto {
    @Schema(description = "Name of the product to be ordered")
    private String productName;
    @Schema(description = "Number of units of the product to be ordered")
    private Integer count;
}
