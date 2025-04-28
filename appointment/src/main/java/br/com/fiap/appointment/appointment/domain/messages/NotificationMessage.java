package br.com.fiap.appointment.appointment.domain.messages;

import br.com.fiap.appointment.appointment.domain.enums.AppointmentStatus;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record NotificationMessage(String email, AppointmentStatus event, ZonedDateTime date) {
}
