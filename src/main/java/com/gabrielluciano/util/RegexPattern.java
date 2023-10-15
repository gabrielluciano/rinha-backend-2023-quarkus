package com.gabrielluciano.util;

public class RegexPattern {

    public static final String NOT_NUMBER = "^(?!-?\\d+$).*";
    public static final String VALID_DATE = "\\b\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])\\b";

    private RegexPattern() {
    }
}
