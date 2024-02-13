package br.com.mmmsieto.financial.annotation.validator;

import br.com.mmmsieto.financial.annotation.cache.ValidationCache;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;

import java.lang.reflect.Field;
import java.util.List;

public class Validator {

    public static void validate(Object object) throws IllegalAccessException, ValidationException {
        List<Field> mandatoryFields = ValidationCache.getMandatoryFields(object.getClass());
        for (Field field : mandatoryFields) {
            field.setAccessible(true);
            if (field.get(object) == null) {
                throw new ValidationException(field.getName() + " is mandatory and cannot be null.");
            }
        }
    }
}
