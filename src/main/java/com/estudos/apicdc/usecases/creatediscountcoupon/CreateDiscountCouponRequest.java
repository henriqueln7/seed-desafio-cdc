package com.estudos.apicdc.usecases.creatediscountcoupon;

import com.estudos.apicdc.domain.Coupon;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateDiscountCouponRequest {
    @NotBlank
    public final String code;
    public final @Positive @Max(1) BigDecimal discountPercentage;
    @NotNull
    @FutureOrPresent
    public final LocalDate expirationDate;

    public CreateDiscountCouponRequest(String code, @Positive @Max(1) BigDecimal discountPercentage, LocalDate expirationDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public Coupon toModel() {
        return new Coupon(this.code, this.discountPercentage, this.expirationDate);
    }
}
