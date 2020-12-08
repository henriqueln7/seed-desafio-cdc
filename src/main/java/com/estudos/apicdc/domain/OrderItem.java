package com.estudos.apicdc.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Embeddable
public class OrderItem {
    @ManyToOne
    private Book book;
    @NotNull @Positive
    private int quantity;
    private BigDecimal momentPrice;

    @Deprecated
    protected OrderItem(){}

    public OrderItem(@NotNull @Valid Book book, @NotNull @Positive int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.momentPrice = book.getPrice();
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

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }
}
