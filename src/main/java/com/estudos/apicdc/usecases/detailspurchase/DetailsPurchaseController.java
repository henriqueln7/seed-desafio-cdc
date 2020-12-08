package com.estudos.apicdc.usecases.detailspurchase;

import com.estudos.apicdc.domain.Purchase;
import com.estudos.apicdc.domain.PurchaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DetailsPurchaseController {

    private final PurchaseRepository purchaseRepository;

    public DetailsPurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseResponse> detailPurchase(@PathVariable("id") Long purchaseId) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);

        if (optionalPurchase.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PurchaseResponse response = new PurchaseResponse(optionalPurchase.get());
        return ResponseEntity.ok(response);
    }
}
