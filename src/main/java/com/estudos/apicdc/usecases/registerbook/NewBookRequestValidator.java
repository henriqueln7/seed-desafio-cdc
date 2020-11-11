package com.estudos.apicdc.usecases.registerbook;

import com.estudos.apicdc.domain.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewBookRequestValidator implements Validator {

    private BookRepository bookRepository;

    public NewBookRequestValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewBookRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NewBookRequest request = (NewBookRequest) o;
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            errors.rejectValue("isbn", null, "ISBN already exists");
        }
        if (bookRepository.existsByTitle(request.getTitle())) {
            errors.rejectValue("title", null, "Title already exists");
        }
    }
}
