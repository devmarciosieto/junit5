package br.com.mmmsieto.financial.validation.impl;

import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.validation.ValidationStrategy;

public class UserValidation implements ValidationStrategy {
    @Override
    public void validate(Object value) throws ValidationException {
        if (value == null) {
            throw new ValidationException("User is mandatory");
        }
    }
}