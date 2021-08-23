package practice.validation.validator;

import practice.validation.annotations.Letters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LettersValidator implements ConstraintValidator<Letters, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
