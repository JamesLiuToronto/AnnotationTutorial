package com.peak.annotationtutorial.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ColorValidator implements ConstraintValidator<ColorValidation, String>
{
    public boolean isValid(String colorName, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"RED","GREEN","BLUE"});
        if (colorName.equalsIgnoreCase("BLACK"))
            return true ;
        return list.contains(colorName);
    }
}
