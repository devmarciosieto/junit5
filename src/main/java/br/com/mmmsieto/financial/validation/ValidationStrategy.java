package br.com.mmmsieto.financial.validation;

import br.com.mmmsieto.financial.domain.exceptions.ValidationException;

public interface ValidationStrategy {
    void validate(Object value) throws ValidationException;
}
