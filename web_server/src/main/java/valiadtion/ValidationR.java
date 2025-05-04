package valiadtion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ValidatorR.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationR {
    String message() default "Invalid value for R. Must be between 1 and 4.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
