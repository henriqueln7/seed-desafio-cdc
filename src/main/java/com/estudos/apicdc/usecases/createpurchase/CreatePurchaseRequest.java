package com.estudos.apicdc.usecases.createpurchase;

import com.estudos.apicdc.domain.Country;
import com.estudos.apicdc.domain.CountryState;
import com.estudos.apicdc.domain.Order;
import com.estudos.apicdc.domain.Purchase;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

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
    private OrderRequest order;

    public CreatePurchaseRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank String surname, String document, @NotBlank String address, @NotBlank String addressComplement, @NotBlank String city, @NotNull Long countryId, Long countryStateId, @NotBlank String phoneNumber, @NotBlank String cep, @NotNull @Valid OrderRequest order) {
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
    }

    @Override
    public String toString() {
        return "CreatePurchaseRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", document='" + document + '\'' +
                ", address='" + address + '\'' +
                ", addressComplement='" + addressComplement + '\'' +
                ", city='" + city + '\'' +
                ", countryId=" + countryId +
                ", countryStateId=" + countryStateId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", CEP='" + cep + '\'' +
                ", cart=" + order +
                '}';
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getCountryStateId() {
        return countryStateId;
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

    public Purchase toModel(EntityManager manager) {
        Country country = manager.find(Country.class, this.countryId);
        Assert.notNull(country, "CountryId is not valid");

        Function<Purchase, Order> createOrder = this.order.toModel(manager);
        Purchase purchase = new Purchase(this.email, this.name, this.surname, this.document, this.address, this.addressComplement, this.city, country, this.phoneNumber, this.cep, createOrder);

        if (this.countryStateId != null) {
            CountryState state = manager.find(CountryState.class, this.countryStateId);
            Assert.notNull(state, "CountryStateId is not valid");
            purchase.setState(state);
        }
        return purchase;
    }

}