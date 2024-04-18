package com.pridanov.mcs2.repository;

import com.pridanov.mcs2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = """
            SELECT t
            FROM Order t
            WHERE t.dateOfOrder = :dateOfOrder
            AND t.totalCost = (
                SELECT MAX(x.totalCost)
                FROM Order x
                WHERE x.dateOfOrder = :dateOfOrder)
            """)
    List<Order> findByPriceAndDate(@Param("dateOfOrder")LocalDate dateOfOrder);

    @Query(value = """
        SELECT t.orderId
        FROM Order t
        WHERE t.dateOfOrder BETWEEN :dateFrom AND :dateTo
        AND t.orderId NOT IN (
            SELECT s.order.orderId
            FROM OrderDetails s
            WHERE s.productName = :productName
        )
      """)
    List<Long> findByDateWithoutProduct(@Param("dateFrom") LocalDate dateFrom,
                                        @Param("dateTo") LocalDate dateTo,
                                        @Param("productName") String productName);
}
