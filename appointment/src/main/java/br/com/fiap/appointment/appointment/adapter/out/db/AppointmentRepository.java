package br.com.fiap.appointment.appointment.adapter.out.db;

import br.com.fiap.appointment.appointment.domain.exception.AppointmentNotFoundException;
import br.com.fiap.appointment.appointment.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    default Appointment findByIdOrElseThrowException(Long id) {
        return findById(id).orElseThrow(AppointmentNotFoundException::new);
    }

}
