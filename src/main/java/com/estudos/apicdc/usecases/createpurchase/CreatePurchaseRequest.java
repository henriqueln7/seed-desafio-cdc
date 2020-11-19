package com.estudos.apicdc.usecases.createpurchase;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    // TODO Validação de estado ser obrigatório se o país tiver estados
    private final Long countryStateId;
    @NotBlank
    private final String phoneNumber;
    @NotBlank
    private final String CEP;

    public CreatePurchaseRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank String surname, String document, @NotBlank String address, @NotBlank String addressComplement, @NotBlank String city, @NotNull Long countryId, Long countryStateId, @NotBlank String phoneNumber, @NotBlank String CEP) {
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
        this.CEP = CEP;
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
                ", CEP='" + CEP + '\'' +
                '}';
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getCountryStateId() {
        return countryStateId;
    }

//    public boolean hasCountryStateValid(EntityManager manager) {
//
//        if (this.countryStateId != null) {
//            Country country = manager.find(Country.class, this.countryId);
//            CountryState countryState = manager.find(CountryState.class, this.countryStateId);
//            if (country == null || countryState == null) {
//                return false;
//            }
//            return country.hasStates() && countryState.belongsTo(country);
//        }
//
//        return true;
//
//    }
}