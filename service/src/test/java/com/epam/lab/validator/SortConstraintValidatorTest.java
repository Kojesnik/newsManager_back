package com.epam.lab.validator;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SortConstraintValidatorTest {

    private static SortConstraintValidator validator;

    @BeforeClass
    public static void setUp() {
        validator = new SortConstraintValidator();
    }

    @Test
    public void isValidWithAuthorSort() {
        String sortType = "author";
        boolean expected = true;
        boolean actual = validator.isValid(sortType, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithDateSort() {
        String sortType = "date";
        boolean expected = true;
        boolean actual = validator.isValid(sortType, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidSort() {
        String sortType = "invalid";
        boolean expected = false;
        boolean actual = validator.isValid(sortType, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithNullSort() {
        String sortType = null;
        boolean expected = true;
        boolean actual = validator.isValid(sortType, null);
        assertEquals(expected, actual);
    }

}