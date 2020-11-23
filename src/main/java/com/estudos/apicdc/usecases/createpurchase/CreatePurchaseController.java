package com.estudos.apicdc.usecases.createpurchase;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CreatePurchaseController {

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new PurchaseCountryValidValidator(manager));
    }

    @PostMapping("/purchases")
    public void createPurchase(@RequestBody @Valid CreatePurchaseRequest request) {
        System.out.println(request);
    }
}
