package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.State;

public class StateValidation implements ConstraintValidator<State, String>{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return false;
        }
        if ("已发布".equals(s) || "草稿".equals(s)) {
            return true;
        }
        return false;
    }
}
