package br.com.mmmsieto.financial.domain.exceptions;

public class ValidationException extends NoStacktraceException {
    public ValidationException(final String message) {
        super(message);
    }
}
