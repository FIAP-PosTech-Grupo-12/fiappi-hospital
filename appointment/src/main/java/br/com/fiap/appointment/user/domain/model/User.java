package br.com.fiap.appointment.user.domain.model;

import br.com.fiap.appointment.user.domain.enums.AccessLevel;
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
@Table(name = "users")
public class User {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq",
            sequenceName = "user_seq", schema = "fiap", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    @Getter
    private String email;

    private ZonedDateTime updatedAt;

    public boolean isDoctorOrNurse() {
        return (AccessLevel.MEDICO.equals(accessLevel) || AccessLevel.ENFERMEIRO.equals(accessLevel));
    }

}
