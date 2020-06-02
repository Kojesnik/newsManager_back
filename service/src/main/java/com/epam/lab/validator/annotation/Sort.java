package com.epam.lab.validator.annotation;

import com.epam.lab.validator.SortConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SortConstraintValidator.class)
@Target( {ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Sort {

    String message() default "{Invalid sort type}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
