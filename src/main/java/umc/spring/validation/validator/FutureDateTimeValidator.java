package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.validation.annotation.FutureDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FutureDateTimeValidator implements ConstraintValidator<FutureDateTime, LocalDateTime> {
    @Override
    public void initialize(FutureDateTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value.isAfter(LocalDateTime.now());
    }
}
