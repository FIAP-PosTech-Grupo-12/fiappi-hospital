package br.com.fiap.appointment.user.adapter.in.api;

import br.com.fiap.appointment.user.adapter.in.api.dto.CreateUserDTO;
import br.com.fiap.appointment.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<String> createUser(@Validated @RequestBody CreateUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body("ID: ".concat(service.createUser(dto).toString()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
