package umc.spring.validation.validator;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_NEGATIVE.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
