package br.com.fiap.appointment.appointment.domain.service;

import br.com.fiap.appointment.appointment.adapter.in.api.dto.CreateAppointmentDTO;
import br.com.fiap.appointment.appointment.adapter.in.api.dto.GetAppointmentDTO;
import br.com.fiap.appointment.appointment.adapter.in.api.dto.UpdateAppointmentDTO;
import br.com.fiap.appointment.appointment.adapter.out.db.AppointmentRepository;
import br.com.fiap.appointment.appointment.domain.enums.AppointmentStatus;
import br.com.fiap.appointment.appointment.domain.exception.AccessNotPermittedException;
import br.com.fiap.appointment.appointment.domain.exception.AppointmentNotFoundException;
import br.com.fiap.appointment.appointment.domain.messages.NotificationMessage;
import br.com.fiap.appointment.appointment.domain.model.Appointment;
import br.com.fiap.appointment.config.amqp.RabbitMQConfig;
import br.com.fiap.appointment.user.domain.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final RabbitTemplate rabbitTemplate;

    private final AppointmentRepository repository;

    private final UserService userService;

    public Long createAppointment(CreateAppointmentDTO dto) {
        var user = userService.findByIdOrElseThrowException(dto.userId());

        var appointment = repository.save(Appointment.builder()
                .user(user)
                .status(AppointmentStatus.PENDING)
                .appointmentDate(dto.date())
                .updatedAt(ZonedDateTime.now())
                .build());

        var message = NotificationMessage.builder()
                .email(user.getEmail())
                .event(AppointmentStatus.PENDING)
                .date(appointment.getAppointmentDate())
                .build();

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);

        return appointment.getId();
    }

    public void updateAppointment(Long appointmentId, UpdateAppointmentDTO dto) {
        var user = userService.findByIdOrElseThrowException(dto.userId());

        if (!user.isDoctorOrNurse())
            throw new AccessNotPermittedException("Acesso não permitido.");

        var appointment = repository.findByIdOrElseThrowException(appointmentId);
        appointment.updateStatus(dto.status());
        repository.save(appointment);

        var message = NotificationMessage.builder()
                .email(user.getEmail())
                .event(dto.status())
                .date(appointment.getAppointmentDate())
                .build();

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
    }

    public List<GetAppointmentDTO> getAppointmentsByUserIdAndPeriod(Long userId, String period) {

        String startDate = null, endDate = null;

        if (Objects.equals(period, "PAST")) {
            endDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace("T", " ");
        } else if (Objects.equals(period, "FUTURE")) {
            startDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace("T", " ");
        }

        var appointments = repository.findAllByUserIdAndAppointmentDateBetween(userId, startDate, endDate);

        if (appointments.isEmpty()) { throw new AppointmentNotFoundException(); }

        return appointments.stream()
                .map(a -> new GetAppointmentDTO(a.getId(),
                        a.getUser().getId(),
                        a.getUser().getName(),
                        a.getStatus(),
                        a.getAppointmentDate(),
                        a.getUpdatedAt()))
                .toList();
    }
}
