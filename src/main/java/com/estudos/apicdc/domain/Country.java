package com.estudos.apicdc.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "country")
    private Set<CountryState> states;
    @Deprecated
    protected Country(){}

    public Country(String name) {
        this.name = name;
    }

    public boolean hasStates() {
        return !this.states.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
