package br.com.fiap.appointment.appointment.adapter.in.api.dto;

import br.com.fiap.appointment.appointment.domain.enums.AppointmentStatus;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.ZonedDateTime;

@SchemaMapping("Appointment")
public record GetAppointmentDTO(
        Long id,
        Long userId,
        String userName,
        AppointmentStatus status,
        ZonedDateTime appointmentDate,
        ZonedDateTime updatedAt
) {
}
