package br.com.fiap.appointment.appointment.domain.model;

import br.com.fiap.appointment.appointment.domain.enums.AppointmentStatus;
import br.com.fiap.appointment.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_seq")
    @SequenceGenerator(name = "appointments_seq",
            sequenceName = "appointment_seq", schema = "fiap", allocationSize = 1)
    private Long id;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Getter
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Getter
    private ZonedDateTime appointmentDate;

    @Getter
    private ZonedDateTime updatedAt;

    public void updateStatus(AppointmentStatus status) {
        this.status = status;
        this.updatedAt = ZonedDateTime.now();
    }

}
