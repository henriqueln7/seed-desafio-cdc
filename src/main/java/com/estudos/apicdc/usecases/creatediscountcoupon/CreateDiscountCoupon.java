package com.estudos.apicdc.usecases.creatediscountcoupon;

import com.estudos.apicdc.domain.Coupon;
import com.estudos.apicdc.domain.CouponRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CreateDiscountCoupon {

    private final CouponRepository couponRepository;

    public CreateDiscountCoupon(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @PostMapping("/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createDiscountCoupon(@RequestBody @Valid CreateDiscountCouponRequest request) {
        if (couponRepository.findByCode(request.code).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coupon with same code already exists");
        }

        Coupon coupon = request.toModel();
        couponRepository.save(coupon);
    }
}
