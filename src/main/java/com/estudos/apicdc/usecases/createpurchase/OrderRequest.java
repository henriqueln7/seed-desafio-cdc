package com.estudos.apicdc.usecases.createpurchase;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

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

}
