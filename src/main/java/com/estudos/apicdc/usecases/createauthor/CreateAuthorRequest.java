package com.estudos.apicdc.usecases.createauthor;

import com.estudos.apicdc.domain.Author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAuthorRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 400)
    private String description;
    @NotBlank
    @Email
    private String email;

    public CreateAuthorRequest(@NotBlank String name, @NotBlank @Size(max = 400) String description, @NotBlank @Email String email) {
        this.name = name;
        this.description = description;
        this.email = email;
    }

    public Author toModel() {
        return new Author(name, description, email);
    }
}
