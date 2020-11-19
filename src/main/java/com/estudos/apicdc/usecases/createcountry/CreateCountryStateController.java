package com.estudos.apicdc.usecases.createcountry;

import com.estudos.apicdc.domain.Country;
import com.estudos.apicdc.domain.CountryRepository;
import com.estudos.apicdc.domain.CountryState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CreateCountryStateController {

    @PersistenceContext
    private EntityManager manager;

    private final CountryRepository countryRepository;

    public CreateCountryStateController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("/countries/{countryId}/states")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createCountryState(@PathVariable("countryId") Long countryId,
                                   @Valid @RequestBody CreateCountryStateRequest request) {
        Country country = countryRepository.findById(countryId)
                                           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CountryID does not exist"));
        CountryState countryState = request.toModel(country);

        // Experimentando brincar com a JPQL :)
        List<CountryState> states = manager.createQuery("SELECT state FROM  CountryState state WHERE state.name = :name AND state.country.id = :countryId", CountryState.class)
                                           .setParameter("name", request.getName())
                                           .setParameter("countryId", countryId)
                                           .getResultList();
        if (!states.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country with that name already exists");
        }
        manager.persist(countryState);
    }
}
