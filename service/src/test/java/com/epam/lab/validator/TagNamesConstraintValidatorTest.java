package com.epam.lab.validator;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TagNamesConstraintValidatorTest {

    private static TagNamesConstraintValidator validator;

    @BeforeClass
    public static void setUp() {
        validator = new TagNamesConstraintValidator();
    }

    @Test
    public void isValid() {
        List<String> tagNames = new ArrayList<>();
        Collections.addAll(tagNames, "epam", "food", "cars");
        boolean expected = true;
        boolean actual = validator.isValid(tagNames, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithNullName() {
        List<String> tagNames = new ArrayList<>();
        Collections.addAll(tagNames, "epam", null, "cars");
        boolean expected = false;
        boolean actual = validator.isValid(tagNames, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidNameMinLength() {
        List<String> tagNames = new ArrayList<>();
        Collections.addAll(tagNames, "epam", "", "cars");
        boolean expected = false;
        boolean actual = validator.isValid(tagNames, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithInvalidNameMaxLength() {
        List<String> tagNames = new ArrayList<>();
        Collections.addAll(tagNames, "epam", "food", "carssssssssssssssssssssssssssssssssssssssssssssssssss");
        boolean expected = false;
        boolean actual = validator.isValid(tagNames, null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidWithNullList() {
        List<String> tagNames = null;
        boolean expected = true;
        boolean actual = validator.isValid(tagNames, null);
        assertEquals(expected, actual);
    }

}