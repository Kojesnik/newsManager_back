package com.epam.lab.validator;

import com.epam.lab.validator.annotation.TagNames;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class TagNamesConstraintValidator implements ConstraintValidator<TagNames, List<String>> {

    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 30;

    @Override
    public void initialize(TagNames tagNames) {

    }

    @Override
    public boolean isValid(List<String> tagNames, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(tagNames)) {
            return true;
        }
        for (String name : tagNames) {
            if (Objects.isNull(name) || name.length() <= MIN_LENGTH || name.length() > MAX_LENGTH) {
                return false;
            }
        }
        return true;
    }
}
