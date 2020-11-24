package com.estudos.apicdc.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //Nome diferente para poder criar a tabela
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Positive
    private BigDecimal total;
    @NotEmpty @Size(min = 1) @Valid
    @ElementCollection
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne(mappedBy = "order")
    private Purchase purchase;

    @Deprecated
    protected Order(){}

    public Order(@NotNull @Valid Purchase purchase, @NotEmpty @Size(min = 1) @Valid List<OrderItem> orderItems, @NotNull @Positive BigDecimal total) {
        BigDecimal orderTotal = orderItems.stream()
                                          .map(OrderItem::getItemPrice)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.isTrue(total.compareTo(orderTotal) == 0, "Total passed is not valid");
        this.purchase = purchase;
        this.orderItems.addAll(orderItems);
        this.total = total;

    }

    @Override
    public String toString() {
        return "Order{" +
                "total=" + total +
                ", orderItems=" + orderItems +
                '}';
    }
}
