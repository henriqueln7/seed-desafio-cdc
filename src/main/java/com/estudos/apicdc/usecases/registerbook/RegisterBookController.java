package com.estudos.apicdc.usecases.registerbook;

import com.estudos.apicdc.domain.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class RegisterBookController {

    @PersistenceContext
    private EntityManager manager;
    private NewBookRequestValidator newBookRequestValidator;

    public RegisterBookController(NewBookRequestValidator newBookRequestValidator) {
        this.newBookRequestValidator = newBookRequestValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(newBookRequestValidator);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void registerBook(@Valid @RequestBody NewBookRequest request) {
        Book book = request.toModel(manager);
        manager.persist(book);
    }
}
