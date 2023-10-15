package com.gabrielluciano.data.external.jpa.constraints;

import com.gabrielluciano.util.RegexPattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNumberValidator implements ConstraintValidator<NotNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.matches(RegexPattern.NOT_NUMBER);
    }
}
