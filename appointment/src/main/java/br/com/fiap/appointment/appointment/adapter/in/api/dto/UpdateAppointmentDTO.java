package br.com.fiap.appointment.appointment.adapter.in.api.dto;

import br.com.fiap.appointment.appointment.domain.enums.AppointmentStatus;

public record UpdateAppointmentDTO(Long userId, AppointmentStatus status) {
}
