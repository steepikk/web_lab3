package ru.itmo.web.valiadtion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorY implements ConstraintValidator<ValidationY, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        double roundedValue = Math.round(value * 100.0) / 100.0;

        return roundedValue >= -5.0 && roundedValue <= 3.0;
    }
}
