package com.pridanov.mcs2.controller;

import com.pridanov.mcs2.entity.Order;
import com.pridanov.mcs2.entity.dto.OrderRequestDto;
import com.pridanov.mcs2.service.OrderService;
import com.pridanov.mcs2.util.dto.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Order Management", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Creating a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The new order has been successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: A record with the same ID already exists in the database.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })

    @PostMapping()
    public ResponseEntity<Order> newOrder (@RequestBody OrderRequestDto order){
        return ResponseEntity.ok(orderService.newOrder(order));
    }

    @Operation(summary = "Get the order with the highest price on a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The most expensive product on the specified date",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid date format", content = @Content),
            @ApiResponse(responseCode = "404", description = "No orders for the selected date",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/by-price-and-date")
    public ResponseEntity<List<Order>> findByPriceAndDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(orderService.findByPriceAndDate(date));
    }

    @Operation(summary = "Get a list of order IDs that do not contain a specified item and were received within a given time period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of order IDs"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "No orders for the selected time period",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/by-date-without-product")
    public ResponseEntity<List<Long>> findByDateWithoutProduct(@RequestParam LocalDate dateFrom,
                                                               @RequestParam LocalDate dateTo,
                                                               @RequestParam String productName){
        return ResponseEntity.ok(orderService.findByDateWithoutProduct(dateFrom, dateTo, productName));
    }
}
