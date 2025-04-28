package br.com.fiap.appointment.appointment.adapter.in.api.dto;

import java.time.ZonedDateTime;

public record CreateAppointmentDTO(Long userId, ZonedDateTime date) {
}
