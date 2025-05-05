package br.com.fiap.appointment.appointment.adapter.out.db;

import br.com.fiap.appointment.appointment.domain.exception.AppointmentNotFoundException;
import br.com.fiap.appointment.appointment.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    default Appointment findByIdOrElseThrowException(Long id) {
        return findById(id).orElseThrow(AppointmentNotFoundException::new);
    }

    @Query(value = """
                    SELECT *
                      FROM appointments
                     WHERE (user_id = :userId OR :userId IS NULL)
                       AND (appointment_date >= TO_TIMESTAMP(:startDate, 'YYYY-MM-DD HH24:MI:SS') OR :startDate IS NULL)
                       AND (appointment_date <= TO_TIMESTAMP(:endDate, 'YYYY-MM-DD HH24:MI:SS') OR :endDate IS NULL)
                    ORDER BY appointment_date
                    """, nativeQuery = true)
    List<Appointment> findAllByUserIdAndAppointmentDateBetween(Long userId, String startDate, String endDate);
}
