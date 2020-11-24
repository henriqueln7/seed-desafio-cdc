package com.estudos.apicdc.usecases.createpurchase;

import com.estudos.apicdc.domain.Order;
import com.estudos.apicdc.domain.OrderItem;
import com.estudos.apicdc.domain.Purchase;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderRequest {
    @NotNull
    @Positive
    public final BigDecimal total;
    @NotNull
    @NotEmpty
    @Valid
    public final List<OrderItemRequest> items;

    public OrderRequest(@NotNull @Positive BigDecimal total, @NotNull @NotEmpty @Valid List<OrderItemRequest> items) {
        this.total = total;
        this.items = items;
    }


    @Override
    public String toString() {
        return "PurchaseCartRequest{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }

    public Function<Purchase, Order> toModel(EntityManager manager) {
        List<OrderItem> orderItems = this.items.stream()
                                               .map(item -> item.toModel(manager))
                                               .collect(Collectors.toList());
        return (purchase) -> new Order(purchase, orderItems, this.total);
    }

}
