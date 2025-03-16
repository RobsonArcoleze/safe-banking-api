package br.com.tokiomarine.safebankingapi.interfaces.rest.handler;

import lombok.*;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse() {
    }
}
