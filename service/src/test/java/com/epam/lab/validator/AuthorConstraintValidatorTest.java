package com.epam.lab.validator;

import com.epam.lab.dto.impl.AuthorDto;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthorConstraintValidatorTest {

    private static AuthorConstraintValidator validator;

    @BeforeClass
    public static void setUp() {
        validator = new AuthorConstraintValidator();
    }

    @Test
    public void isValid() {
        AuthorDto authorDto = new AuthorDto("Misha", "Kolesnik");
        boolean expected = true;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithNullName() {
        AuthorDto authorDto = new AuthorDto(null, "Kolesnik");
        boolean expected = false;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithNullSurname() {
        AuthorDto authorDto = new AuthorDto("Misha", null);
        boolean expected = false;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidNameMinLength() {
        AuthorDto authorDto = new AuthorDto("", "Kolesnik");
        boolean expected = false;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidNameMaxLength() {
        AuthorDto authorDto = new AuthorDto("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", "Kolesnik");
        boolean expected = false;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidSurnameMinLength() {
        AuthorDto authorDto = new AuthorDto("Misha", "");
        boolean expected = false;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidSurnameMaxLength() {
        AuthorDto authorDto = new AuthorDto("Misha", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        boolean expected = false;
        boolean actual = validator.isValid(authorDto, null);
        assertEquals(expected, actual);
    }

}