package eu.planlos.javaspringwebutilities.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ThingValidator implements ConstraintValidator<ValidThingName, String> {

    @Override
    public void initialize(ValidThingName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String thingName, ConstraintValidatorContext context) {
        if (thingName == null) {
            return true;
        }
        return "VALID THING".equals(thingName);
    }
}