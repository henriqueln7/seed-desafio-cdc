package com.estudos.apicdc.usecases.createcountry;

import com.estudos.apicdc.domain.CountryRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CountryUniqueNameValidator implements Validator {

    private CountryRepository countryRepository;

    public CountryUniqueNameValidator(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateCountryRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CreateCountryRequest request = (CreateCountryRequest) o;

        if (countryRepository.findByName(request.getName()).isPresent()) {
            errors.rejectValue("name", null, "Country name already exists");
        }

    }
}
