package br.com.fiap.appointment.appointment.adapter.in.api;

import br.com.fiap.appointment.appointment.adapter.in.api.dto.GetAppointmentDTO;
import br.com.fiap.appointment.appointment.domain.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("/graphql")
@RequiredArgsConstructor
public class AppointmentGraphQLController {

    private final AppointmentService appointmentService;

    @QueryMapping
    public List<GetAppointmentDTO> getAppointmentsByPeriod(@Argument("period") String period) {
        return appointmentService.getAppointmentsByUserIdAndPeriod(null, period);
    }

    @QueryMapping
    public List<GetAppointmentDTO> getAppointmentsByUserIdAndPeriod(@Argument("userId") Long userId, @Argument("period") String period) {
        return appointmentService.getAppointmentsByUserIdAndPeriod(userId, period);
    }
}
