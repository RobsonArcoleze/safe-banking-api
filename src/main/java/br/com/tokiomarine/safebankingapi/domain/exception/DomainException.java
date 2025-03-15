package br.com.tokiomarine.safebankingapi.domain.exception;

public class DomainException extends RuntimeException {

    private final String error;

    public DomainException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
