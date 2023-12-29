package eu.planlos.javaspringwebutilities.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ThingValidator.class)
public @interface ValidThingName {
    String message() default "Invalid thing";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

