package com.estudos.apicdc.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CountryState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Valid @NotNull
    @ManyToOne
    private Country country;

    @Deprecated
    protected CountryState(){}

    public CountryState(@NotBlank String name, @Valid @NotNull Country country) {
        this.name = name;
        this.country = country;
    }
}
