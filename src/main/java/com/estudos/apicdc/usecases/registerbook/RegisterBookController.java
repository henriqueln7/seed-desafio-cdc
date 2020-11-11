package com.estudos.apicdc.usecases.registerbook;

import com.estudos.apicdc.domain.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class RegisterBookController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void registerBook(@Valid @RequestBody NewBookRequest request) {
        Book book = request.toModel(manager);
        manager.persist(book);
    }
}
