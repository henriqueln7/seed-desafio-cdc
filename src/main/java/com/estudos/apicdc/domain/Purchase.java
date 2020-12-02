package com.estudos.apicdc.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    @OneToOne(cascade = CascadeType.PERSIST)
    private Order order;
    @ManyToOne
    private CountryState state;

    @Deprecated
    protected Purchase(){}

    public Purchase(String email, String name, String surname, String document, String address, String addressComplement, String city, Country country, String phoneNumber, String cep, Function<Purchase, Order> createOrder) {
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
        this.order = createOrder.apply(this);
    }


    public void setState(@Valid @NotNull CountryState state) {
        Assert.notNull(state, "CountryState is null");
        Assert.notNull(this.country, "You cannot associate a state to a null country");
        Assert.isTrue(state.belongsTo(this.country), "This state does not belong to country present on purchase");
        this.state = state;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", document='" + document + '\'' +
                ", address='" + address + '\'' +
                ", addressComplement='" + addressComplement + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cep='" + cep + '\'' +
                ", order=" + order +
                ", state=" + state +
                '}';
    }

    public Long getId() {
        return this.id;
    }
}
