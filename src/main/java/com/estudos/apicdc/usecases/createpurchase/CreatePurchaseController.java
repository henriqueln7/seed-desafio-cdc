package com.estudos.apicdc.usecases.createpurchase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreatePurchaseController {

    @PostMapping("/purchases")
    public void createPurchase(@RequestBody @Valid CreatePurchaseRequest request) {
        System.out.println(request);
    }
}
