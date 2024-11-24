package ru.itmo.web.valiadtion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ValidatorY.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationY {
    String message() default "Invalid value for Y. Must be between -5 and 3.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
