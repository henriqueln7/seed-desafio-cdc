package com.estudos.apicdc.domain;

import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class Order {
    @NotNull @Positive
    private final BigDecimal total;
    @NotEmpty @Size(min = 1) @Valid
    private final List<OrderItem> orderItems;
    private Purchase purchase;

    public Order(@NotNull @Valid Purchase purchase, @NotEmpty @Size(min = 1) @Valid List<OrderItem> orderItems, @NotNull @Positive BigDecimal total) {
        BigDecimal orderTotal = orderItems.stream()
                                          .map(OrderItem::getItemPrice)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.isTrue(total.compareTo(orderTotal) == 0, "Total passed is not valid");
        this.purchase = purchase;
        this.total = total;
        this.orderItems = orderItems;

    }

    @Override
    public String toString() {
        return "Order{" +
                "total=" + total +
                ", orderItems=" + orderItems +
                '}';
    }
}
