package br.com.fiap.appointment.config.exception;

import br.com.fiap.appointment.appointment.domain.exception.AccessNotPermittedException;
import br.com.fiap.appointment.user.domain.exception.DeleteUserNotPermittedException;
import br.com.fiap.appointment.user.domain.exception.UserNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<String> handleValidationException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("Usuário não encontrado para a operação.");
    }

    @ExceptionHandler(value = AccessNotPermittedException.class)
    public ResponseEntity<String> handleAccessNotPermittedException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = DeleteUserNotPermittedException.class)
    public ResponseEntity<String> handleDeleteUserNotPermittedException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }

}
