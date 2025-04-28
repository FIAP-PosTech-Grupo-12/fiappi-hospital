package br.com.fiap.appointment.user.domain.exception;

public class DeleteUserNotPermittedException extends RuntimeException {

    public DeleteUserNotPermittedException(String message) {
        super(message);
    }

}
