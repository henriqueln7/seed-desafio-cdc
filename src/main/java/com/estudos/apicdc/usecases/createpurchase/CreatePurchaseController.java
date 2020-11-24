package com.estudos.apicdc.usecases.createpurchase;

import com.estudos.apicdc.domain.Purchase;
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
    public String createPurchase(@RequestBody @Valid CreatePurchaseRequest request) {
        Purchase purchase = request.toModel(manager);
        return purchase.toString();
    }
}
