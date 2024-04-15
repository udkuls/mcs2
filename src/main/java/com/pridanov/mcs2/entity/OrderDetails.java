package com.pridanov.mcs2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pridanov.mcs2.pk.OrderDetailsPK;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_details")
@Entity
@IdClass(OrderDetailsPK.class)
@Schema(description = "Entity for representing order details")
public class OrderDetails {
    @Schema(description = "Unique identifier for the order detail")
    @Id
    @Column(name = "id")
    private Long id;
    @JsonIgnore
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Schema(description = "Name of the product in the order detail")
    @Column(name = "product_name")
    private String productName;

    @Schema(description = "Count of the product in the order detail")
    @Column(name = "count")
    private Integer count;
}
