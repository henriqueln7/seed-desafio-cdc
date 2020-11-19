package com.estudos.apicdc.usecases.createcountry;

import com.estudos.apicdc.domain.Country;
import com.estudos.apicdc.domain.CountryState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CreateCountryStateRequest {
    @NotBlank
    private final String name;

    @JsonCreator
    public CreateCountryStateRequest(@JsonProperty("name") @NotBlank String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateCountryStateRequest{" +
                "name='" + name + '\'' +
                '}';
    }

    public CountryState toModel(Country country) {
        return new CountryState(this.name, country);
    }

    public String getName() {
        return this.name;
    }
}
