package com.estudos.apicdc.domain;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String code;
    private @Positive @Max(1) BigDecimal discountPercentage;
    private @NotNull @FutureOrPresent LocalDate expirationDate;

    @Deprecated
    protected Coupon(){}

    public Coupon(@NotBlank String code, @Positive @Max(1) BigDecimal discountPercentage, @NotNull @Future LocalDate expirationDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public boolean isValid() {
        return this.expirationDate.compareTo(LocalDate.now()) >= 0;
    }

    /**
     * Aplica o desconto em um preço passado como parâmetro.
     * @param price Preço a ser aplicado o desconto.
     * @return Valor do preço com o desconto.
     */
    public BigDecimal giveDiscount(@NotNull @Positive BigDecimal price) {
        Assert.notNull(price, "You should not pass a null price");
        Assert.isTrue(price.compareTo(BigDecimal.ZERO) > 0, "You should pass a positive price");

        return price.multiply(new BigDecimal("1").subtract(this.discountPercentage));
    }
}
