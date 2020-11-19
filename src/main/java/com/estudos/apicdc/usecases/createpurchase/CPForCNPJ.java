package com.estudos.apicdc.usecases.createpurchase;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CPForCNPJValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CPForCNPJ {

    String message() default "{com.estudos.apicdc.documentinvalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };


}
class CPForCNPJValidator implements ConstraintValidator<CPForCNPJ, String> {
    @Override
    public void initialize(CPForCNPJ constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(s, constraintValidatorContext) || cnpjValidator.isValid(s, constraintValidatorContext);
    }
}