package br.com.fiap.appointment.user.domain.service;

import br.com.fiap.appointment.user.adapter.in.api.dto.CreateUserDTO;
import br.com.fiap.appointment.user.adapter.out.db.UserRepository;
import br.com.fiap.appointment.user.domain.exception.DeleteUserNotPermittedException;
import br.com.fiap.appointment.user.domain.model.User;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final EmailValidator validator = EmailValidator.getInstance();

    private final UserRepository repository;

    public User findByIdOrElseThrowException(Long id) {
        return repository.findByIdOrElseThrowException(id);
    }

    public Long createUser(CreateUserDTO dto) {
        if (!validator.isValid(dto.email()))
            throw new ValidationException("Email não é válido.");

        return repository.save(User.builder()
                .name(dto.name())
                .email(dto.email())
                .accessLevel(dto.accessLevel())
                .updatedAt(ZonedDateTime.now())
                .build()).getId();
    }

    public void deleteUser(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DeleteUserNotPermittedException("Usuário possui consultas ou agendamentos cadastrados.");
        }
    }

}
