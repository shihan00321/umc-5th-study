package umc.spring.validation.annotation;

import umc.spring.validation.validator.FutureDateTimeValidator;
import umc.spring.validation.validator.RestaurantExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER } )
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateTimeValidator.class)
public @interface FutureDateTime {
    String message() default "미션 마감 시간은 현재 시각 이후이어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
