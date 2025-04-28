package br.com.fiap.appointment.appointment.domain.exception;

public class AccessNotPermittedException extends RuntimeException {

    public AccessNotPermittedException(String message) {
        super(message);
    }

}
