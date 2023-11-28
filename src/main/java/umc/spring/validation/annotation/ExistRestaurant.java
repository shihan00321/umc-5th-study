package umc.spring.validation.annotation;


import umc.spring.validation.validator.RestaurantExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER } )
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RestaurantExistValidator.class)
public @interface ExistRestaurant {
    String message() default "해당 식당이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
