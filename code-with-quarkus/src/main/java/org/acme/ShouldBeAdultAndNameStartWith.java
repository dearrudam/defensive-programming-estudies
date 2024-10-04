package org.acme;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
        validatedBy = {ShouldBeAdultAndNameStartWith.Validator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShouldBeAdultAndNameStartWith {

    String value();

    boolean ignoreCase() default false;

    String message() default "it doesn't match with the specification";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    static class Validator implements ConstraintValidator<ShouldBeAdultAndNameStartWith, RegistrationResource.UserData> {


        private boolean ignoreCase;
        private String expectedInitialName;

        @Override
        public void initialize(ShouldBeAdultAndNameStartWith constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
            this.expectedInitialName = constraintAnnotation.value();
            this.ignoreCase = constraintAnnotation.ignoreCase();
        }

        @Override
        public boolean isValid(RegistrationResource.UserData userData, ConstraintValidatorContext constraintValidatorContext) {

            if (userData.age() < 18)
                return false;

            String name = userData.name();
            String expectedInitialName = this.expectedInitialName;
            if (name == null)
                return false;

            if (this.ignoreCase) {
                name = name.toLowerCase();
                expectedInitialName = expectedInitialName.toLowerCase();
            }

            if (!name.startsWith(expectedInitialName))
                return false;

            return true;
        }
    }
}
