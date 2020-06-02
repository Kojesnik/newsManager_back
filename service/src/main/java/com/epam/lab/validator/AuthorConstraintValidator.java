package com.epam.lab.validator;

import com.epam.lab.dto.impl.AuthorDto;
import com.epam.lab.validator.annotation.Author;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorConstraintValidator implements ConstraintValidator<Author, AuthorDto> {

    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 30;
    private static final String REG_EX = "^[a-zA-Zа-яёА-ЯЁ]+$";

    @Override
    public void initialize(Author author) {

    }

    @Override
    public boolean isValid(AuthorDto authorDto, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(authorDto) && Objects.nonNull(authorDto.getName()) && checkOnRegex(authorDto.getName()) && authorDto.getName().length() > MIN_LENGTH
                && authorDto.getName().length() <= MAX_LENGTH && Objects.nonNull(authorDto.getSurname())
                && checkOnRegex(authorDto.getSurname()) && authorDto.getSurname().length() > MIN_LENGTH
                && authorDto.getSurname().length() <= MAX_LENGTH;
    }

    private boolean checkOnRegex(String string) {
        Pattern pattern = Pattern.compile(REG_EX);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
