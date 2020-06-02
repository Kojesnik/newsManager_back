package com.epam.lab.validator;

import com.epam.lab.type.SortType;
import com.epam.lab.validator.annotation.Sort;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SortConstraintValidator implements ConstraintValidator<Sort, String> {

    @Override
    public void initialize(Sort sort) {

    }

    @Override
    public boolean isValid(String sortType, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(sortType)) {
            return true;
        }
        for (SortType type : SortType.values()) {
            if (type.name().equalsIgnoreCase(sortType)) {
                return true;
            }
        }
        return false;
    }
}
