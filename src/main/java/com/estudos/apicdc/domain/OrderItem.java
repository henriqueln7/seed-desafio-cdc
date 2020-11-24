package com.estudos.apicdc.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OrderItem {
    private final Book book;
    @NotNull @Positive
    private final int quantity;

    public OrderItem(@NotNull @Valid Book book, @NotNull @Positive int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public BigDecimal getItemPrice() {
        return this.book.getPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
