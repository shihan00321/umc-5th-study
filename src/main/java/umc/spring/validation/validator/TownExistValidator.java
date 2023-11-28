package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Town;
import umc.spring.service.TempCommandService;
import umc.spring.validation.annotation.ExistTown;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TownExistValidator implements ConstraintValidator<ExistTown, Long> {

    private final TempCommandService.TownQueryService townQueryService;
    @Override
    public void initialize(ExistTown constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<Town> town = townQueryService.findTown(value);
        if (town.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.TOWN_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
