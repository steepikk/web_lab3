package ru.itmo.web.valiadtion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ValidatorX.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationX {
    String message() default "Invalid value for X. Must be between -4 and 4.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
