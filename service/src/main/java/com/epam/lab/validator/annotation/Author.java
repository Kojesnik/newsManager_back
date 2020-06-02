package com.epam.lab.validator.annotation;

import com.epam.lab.validator.AuthorConstraintValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthorConstraintValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

    String message() default "{Invalid author data}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
