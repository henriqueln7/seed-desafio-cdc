package com.estudos.apicdc.usecases.createcategory;

import com.estudos.apicdc.domain.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CreateCategoryRequest {
    @NotBlank
    public final String name;

    @JsonCreator
    public CreateCategoryRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public Category toModel() {
        return new Category(this.name);
    }
}
