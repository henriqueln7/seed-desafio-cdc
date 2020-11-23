package com.estudos.apicdc.usecases.createpurchase;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

public class PurchaseCountryValidValidator implements Validator {
    private EntityManager manager;

    public PurchaseCountryValidValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreatePurchaseRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CreatePurchaseRequest request = (CreatePurchaseRequest) o;

        if (!request.countryStateIsValid(manager)) {
            errors.rejectValue("countryStateId", null, "CountryState is not valid");
        }
    }
}
