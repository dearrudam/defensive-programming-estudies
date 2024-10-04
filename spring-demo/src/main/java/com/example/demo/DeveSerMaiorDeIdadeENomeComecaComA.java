package com.example.demo;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
        validatedBy = {DeveSerMaiorDeIdadeENomeComecaComA.Validator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeveSerMaiorDeIdadeENomeComecaComA {

    String message() default "it doesn't match with the specification";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    static class Validator implements ConstraintValidator<DeveSerMaiorDeIdadeENomeComecaComA, RegistrationController.UserData> {

        @Override
        public void initialize(DeveSerMaiorDeIdadeENomeComecaComA constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(RegistrationController.UserData userData, ConstraintValidatorContext constraintValidatorContext) {

            if (userData.age() < 18)
                return false;

            String name = userData.name();

            if (name == null)
                return false;

            if (!name.toLowerCase().startsWith("a"))
                return false;

            return true;
        }
    }
}
