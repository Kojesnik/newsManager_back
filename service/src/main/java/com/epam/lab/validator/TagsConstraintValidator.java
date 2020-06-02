package com.epam.lab.validator;

import com.epam.lab.dto.impl.TagDto;
import com.epam.lab.validator.annotation.Tags;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class TagsConstraintValidator implements ConstraintValidator<Tags, List<TagDto>> {

    @Override
    public void initialize(Tags tags) {

    }

    @Override
    public boolean isValid(List<TagDto> tagDtos, ConstraintValidatorContext constraintValidatorContext) {
        for (TagDto tagDto : tagDtos) {
            if (Objects.isNull(tagDto) || Objects.isNull(tagDto.getName()) || tagDto.getName().length() <= 0 || tagDto.getName().length() > 30) {
                return false;
            }
        }
        return true;
    }
}
