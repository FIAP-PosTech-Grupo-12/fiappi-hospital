package br.com.fiap.appointment.appointment.adapter.in.api;

import br.com.fiap.appointment.appointment.adapter.in.api.dto.CreateAppointmentDTO;
import br.com.fiap.appointment.appointment.adapter.in.api.dto.UpdateAppointmentDTO;
import br.com.fiap.appointment.appointment.domain.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/appointment")
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body("ID: ".concat(service.createAppointment(dto).toString()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Long id, @RequestBody UpdateAppointmentDTO dto) {
        service.updateAppointment(id, dto);
        return ResponseEntity.ok().build();
    }

}
