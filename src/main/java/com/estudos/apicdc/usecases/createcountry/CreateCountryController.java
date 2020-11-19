package com.estudos.apicdc.usecases.createcountry;

import com.estudos.apicdc.domain.Country;
import com.estudos.apicdc.domain.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CreateCountryController {

    private final CountryRepository countryRepository;

    public CreateCountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new CountryUniqueNameValidator(countryRepository));
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createCountry(@Valid @RequestBody CreateCountryRequest request) {
        Country country = request.toModel();
        countryRepository.save(country);
    }
}
