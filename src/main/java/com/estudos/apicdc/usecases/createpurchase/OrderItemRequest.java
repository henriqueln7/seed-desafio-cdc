package com.estudos.apicdc.usecases.createpurchase;

import com.estudos.apicdc.domain.Book;
import com.estudos.apicdc.domain.OrderItem;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderItemRequest {
    @NotNull
    private final Long bookId;
    @NotNull
    @Positive
    private final int quantity;


    public OrderItemRequest(@NotNull Long bookId, @NotNull @Positive int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemRequest{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }

    public OrderItem toModel(EntityManager manager) {
        Book book = manager.find(Book.class, this.bookId);
        Assert.notNull(book, "Book is null");
        return new OrderItem(book, quantity);
    }


}
