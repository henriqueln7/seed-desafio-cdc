package com.estudos.apicdc.usecases.detailspurchase;

import com.estudos.apicdc.domain.OrderItem;
import com.estudos.apicdc.domain.Purchase;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseResponse {

    public final Long id;
    public final String name;
    public final String email;
    public final String surname;
    public final String document;
    public final String country;
    public final BigDecimal totalPrice;
    public final String city;
    public final String cep;
    public final String address;
    public final String addressComplement;
    public final String phoneNumber;
    public final boolean hasCoupon;
    public final BigDecimal finalPrice;
    public final List<ItemResponse> items;
    private String state;

    public PurchaseResponse(Purchase purchase) {
        id = purchase.getId();
        name = purchase.getName();
        email = purchase.getEmail();
        surname = purchase.getSurname();
        document = purchase.getDocument();
        country = purchase.getCountry().getName();
        totalPrice = purchase.getTotalPrice();
        city = purchase.getCity();
        cep = purchase.getCep();
        address = purchase.getAddress();
        addressComplement = purchase.getAddressComplement();
        phoneNumber = purchase.getPhoneNumber();
        hasCoupon = purchase.getCoupon().isPresent();
        finalPrice = purchase.calculateFinalPrice();
        items = purchase.getItems().stream().map(ItemResponse::new).collect(Collectors.toList());
        purchase.getState().ifPresent(countryState -> this.state = countryState.getName());
    }

    public String getState() {
        return state;
    }
}

class ItemResponse {

    public final int quantity;
    public final String title;
    public final BigDecimal price;

    public ItemResponse(OrderItem item) {
        quantity = item.getQuantity();
        title = item.getBook().getTitle();
        price = item.getItemPrice();
    }
}