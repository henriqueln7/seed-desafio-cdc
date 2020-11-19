package com.estudos.apicdc.usecases.createcountry;

import com.estudos.apicdc.domain.Country;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CreateCountryRequest {
    @NotBlank
    private String name;

    @JsonCreator
    public CreateCountryRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateCountryRequest{" +
                "name='" + name + '\'' +
                '}';
    }

    public Country toModel() {
        return new Country(this.name);
    }

    public String getName() {
        return name;
    }
}
