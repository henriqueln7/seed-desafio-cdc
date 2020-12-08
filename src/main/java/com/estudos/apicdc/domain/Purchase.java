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
import java.util.Optional;
import java.util.function.Function;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String document;
    private String address;
    private String addressComplement;
    private String city;
    @ManyToOne
    private Country country;
    private String phoneNumber;
    private String cep;
    @ManyToOne
    private CountryState state;
    @ManyToOne
    private Coupon coupon;
    @Positive
    private BigDecimal totalPrice;
    @NotEmpty @Valid
    @ElementCollection
    private final List<OrderItem> items = new ArrayList<>();

    @Deprecated
    protected Purchase(){}

    public Purchase(String email, String name, String surname, String document, String address, String addressComplement, String city, Country country, String phoneNumber, String cep, @NotEmpty @Size(min = 1) @Valid List<OrderItem> orderItems, @NotNull @Positive BigDecimal totalPrice) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.cep = cep;

        BigDecimal orderTotal = orderItems.stream()
                                          .map(OrderItem::getItemPrice)
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);

        Assert.isTrue(totalPrice.compareTo(orderTotal) == 0, "Total passed is not valid");
        this.items.addAll(orderItems);
        this.totalPrice = totalPrice;
    }


    public void setState(@Valid @NotNull CountryState state) {
        Assert.notNull(state, "CountryState is null");
        Assert.notNull(this.country, "You cannot associate a state to a null country");
        Assert.isTrue(state.belongsTo(this.country), "This state does not belong to country present on purchase");
        this.state = state;
    }

    public void addCoupon(Coupon coupon) {
        Assert.state(this.coupon == null, "This purchase already has a coupon. You cannot put another on it");
        Assert.notNull(coupon, "You should not pass a null coupon");
        Assert.state(coupon.isValid(), "Coupon is not valid. It is expired");
        Assert.state(this.id == null, "This purchase is already persisted on database. You can't do this operation.");

        this.coupon = coupon;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
