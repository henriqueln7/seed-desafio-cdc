package com.estudos.apicdc.usecases.createpurchase;

import com.estudos.apicdc.domain.Coupon;
import com.estudos.apicdc.domain.CouponRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class PurchaseCouponValidator implements Validator {

    private CouponRepository couponRepository;

    public PurchaseCouponValidator(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
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
        if (request.getCouponCode().isPresent()) {
            Optional<Coupon> optionalCoupon = couponRepository.findByCode(request.getCouponCode().get());
            if (optionalCoupon.isEmpty()) {
                errors.rejectValue("couponCode", null, "This coupon does not exist");
            } else {
                Coupon coupon = optionalCoupon.get();
                if (!coupon.isValid()) {
                    errors.rejectValue("couponCode", null, "This coupon is not valid anymore. Try another.");
                }
            }
        }
    }
}
