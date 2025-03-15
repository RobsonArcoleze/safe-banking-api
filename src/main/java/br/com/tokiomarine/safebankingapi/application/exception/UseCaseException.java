package br.com.tokiomarine.safebankingapi.application.exception;

public class UseCaseException extends RuntimeException {

    private final String error;
    public UseCaseException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
