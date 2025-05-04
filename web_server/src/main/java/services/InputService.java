package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import entities.ResultEntity;

import java.util.Set;

@ApplicationScoped
public class InputService {

    private final Validator validator;

    public InputService() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    public void validateInput(Double x, Double y, Double r) {
        if (x == null || y == null || r == null) {
            throw new IllegalArgumentException("X, Y, and R must not be null.");
        }

        ResultEntity result = new ResultEntity(null, x, y, r, false);

        Set<ConstraintViolation<ResultEntity>> violations = validator.validate(result);
        if (!violations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
            for (ConstraintViolation<ResultEntity> violation : violations) {
                errorMessages.append(violation.getMessage()).append("<br>");
            }
            throw new IllegalArgumentException(errorMessages.toString());
        }
    }

    public boolean checkPoint(ResultEntity result) {
        return checkPoint(result.getX(), result.getY(), result.getR());
    }

    public boolean checkPoint(Double x, Double y, Double r) {
        if (x >= 0 && y <= 0) {
            return (x * x + y * y <= (r * r / 4));
        } else if (x <= 0 && y >= 0) {
            return (x >= -r && y <= r);
        } else if (x <= 0 && y <= 0) {
            return (y >= -x / 2 - r / 2);
        }
        return false;
    }
}
