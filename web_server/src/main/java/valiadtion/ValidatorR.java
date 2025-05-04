package valiadtion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ValidatorR implements ConstraintValidator<ValidationR, Double> {
    private final Set<Double> validRValues = Set.of(1.0, 2.0, 3.0, 4.0);

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && validRValues.contains(value);
    }
}
