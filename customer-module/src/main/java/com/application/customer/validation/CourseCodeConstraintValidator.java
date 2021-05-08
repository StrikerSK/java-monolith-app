package com.application.customer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    @Override
    public boolean isValid(String inputCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        try {
            result = inputCode.startsWith(coursePrefix);
        } catch (NullPointerException e){
            result = true;
        }

        return result;
    }
}
