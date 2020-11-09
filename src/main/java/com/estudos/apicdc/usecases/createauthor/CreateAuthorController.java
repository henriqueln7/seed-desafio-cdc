package com.estudos.apicdc.usecases.createauthor;

import com.estudos.apicdc.domain.Author;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CreateAuthorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createAuthor(@RequestBody @Valid CreateAuthorRequest request) {
        Author newAuthor = request.toModel();
        manager.persist(newAuthor);
    }
}
