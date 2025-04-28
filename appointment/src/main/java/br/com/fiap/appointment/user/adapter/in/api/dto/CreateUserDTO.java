package br.com.fiap.appointment.user.adapter.in.api.dto;

import br.com.fiap.appointment.user.domain.enums.AccessLevel;

public record CreateUserDTO(String name, AccessLevel accessLevel, String email) {
}
