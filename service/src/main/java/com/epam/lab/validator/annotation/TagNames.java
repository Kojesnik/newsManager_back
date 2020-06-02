package com.epam.lab.validator.annotation;

import com.epam.lab.validator.TagNamesConstraintValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TagNamesConstraintValidator.class)
@Target( {ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface TagNames {

    String message() default "{Invalid tag names}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
