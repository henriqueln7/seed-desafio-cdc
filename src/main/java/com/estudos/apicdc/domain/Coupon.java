package com.estudos.apicdc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String code;
    private @Positive @Max(100) int discountPercentage;
    private @NotNull @FutureOrPresent LocalDate expirationDate;

    @Deprecated
    protected Coupon(){}

    public Coupon(@NotBlank String code, @Positive @Max(100) int discountPercentage, @NotNull @Future LocalDate expirationDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public boolean isValid() {
        return this.expirationDate.compareTo(LocalDate.now()) >= 0;
    }
}
