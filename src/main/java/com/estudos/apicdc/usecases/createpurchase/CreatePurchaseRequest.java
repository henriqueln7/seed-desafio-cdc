package com.estudos.apicdc.usecases.createpurchase;

import com.estudos.apicdc.domain.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreatePurchaseRequest {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String name;
    @NotBlank
    private final String surname;
    @CPForCNPJ
    private final String document;
    @NotBlank
    private final String address;
    @NotBlank
    private final String addressComplement;
    @NotBlank
    private final String city;
    @NotNull
    private final Long countryId;
    private final Long countryStateId;
    @NotBlank
    private final String phoneNumber;
    @NotBlank
    private final String cep;
    @NotNull
    @Valid
    private final OrderRequest order;
    
    private final String couponCode;

    public CreatePurchaseRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank String surname, String document, @NotBlank String address, @NotBlank String addressComplement, @NotBlank String city, @NotNull Long countryId, Long countryStateId, @NotBlank String phoneNumber, @NotBlank String cep, @NotNull @Valid OrderRequest order, String couponCode) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.countryId = countryId;
        this.countryStateId = countryStateId;
        this.phoneNumber = phoneNumber;
        this.cep = cep;
        this.order = order;
        this.couponCode = couponCode;
    }

    /**
     * Necessário para o Jackson conseguir exibir a mensagem correta de validação.
     * @return order
     */
    public OrderRequest getOrder() {
        return order;
    }

    public boolean countryStateIsValid(EntityManager manager) {
        Country country = manager.find(Country.class, this.countryId);
        Assert.notNull(country, "Country is null");
        if (country.hasStates()) {
            if (this.countryStateId == null) {
                return false;
            }
            CountryState state = manager.find(CountryState.class, this.countryStateId);
            Assert.notNull(state, "CountryState is null");
            return state.belongsTo(country);
        }
        return true;
    }

    public Purchase toModel(EntityManager manager, CouponRepository couponRepository) {
        Country country = manager.find(Country.class, this.countryId);
        Assert.notNull(country, "CountryId is not valid");

        List<OrderItem> orderItems = this.order.items.stream()
                                                     .map(item -> item.toModel(manager))
                                                     .collect(Collectors.toList());

        Purchase purchase = new Purchase(this.email, this.name, this.surname, this.document, this.address, this.addressComplement, this.city, country, this.phoneNumber, this.cep, orderItems, this.order.total);

        if (this.countryStateId != null) {
            CountryState state = manager.find(CountryState.class, this.countryStateId);
            Assert.notNull(state, "CountryStateId is not valid");
            purchase.setState(state);
        }

        if (this.hasCouponCode()) {
            Optional<Coupon> optionalCoupon = couponRepository.findByCode(this.couponCode);
            Assert.state(optionalCoupon.isPresent(), "Coupon not valid");
            purchase.addCoupon(optionalCoupon.get());
        }

        return purchase;
    }

    public boolean hasCouponCode() {
        return StringUtils.hasLength(this.couponCode);
    }

    public Optional<String> getCouponCode() {
        return Optional.ofNullable(this.couponCode);
    }
}