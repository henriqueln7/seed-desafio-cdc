package com.estudos.apicdc.usecases.creatediscountcoupon;

import com.estudos.apicdc.domain.Coupon;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CreateDiscountCouponRequest {
    @NotBlank
    public final String code;
    @Positive
    @Max(100)
    public final int discountPercentage;
    @NotNull
    @FutureOrPresent
    public final LocalDate expirationDate;

    public CreateDiscountCouponRequest(String code, int discountPercentage, LocalDate expirationDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CreateDiscountCouponRequest{" +
                "code='" + code + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public Coupon toModel() {
        return new Coupon(this.code, this.discountPercentage, this.expirationDate);
    }
}
