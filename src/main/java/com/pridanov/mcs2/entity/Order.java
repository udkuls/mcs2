package com.pridanov.mcs2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "\"order\"")
@Schema(description = "Entity for representing an order")
public class Order {
    @Schema(description = "Unique identifier for the order")
    @Id
    @Column(name = "id")
    private Long orderId;

    @Schema(description = "Total cost of the order")
    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Schema(description = "Date of the order")
    @Column(name = "date_of_order")
    private LocalDate dateOfOrder;

    @Schema(description = "List of order details for the order")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetails> orderDetailsList = new ArrayList<>();
}
