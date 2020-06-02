package com.epam.lab.validator.annotation;

import com.epam.lab.validator.TagsConstraintValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TagsConstraintValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Tags {

    String message() default "{Invalid tags}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
