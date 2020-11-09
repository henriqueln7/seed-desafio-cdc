package com.estudos.apicdc.usecases.createauthor;

import com.estudos.apicdc.domain.Author;
import com.estudos.apicdc.domain.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CreateAuthorController {

    private final AuthorRepository authorRepository;

    public CreateAuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createAuthor(@RequestBody @Valid CreateAuthorRequest request) {
        if(authorRepository.existsByEmail(request.email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with same email already exists");
        }
        Author newAuthor = request.toModel();
        authorRepository.save(newAuthor);
    }
}
