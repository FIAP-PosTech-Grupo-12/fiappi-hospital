package br.com.fiap.appointment.user.adapter.out.db;

import br.com.fiap.appointment.user.domain.exception.UserNotFoundException;
import br.com.fiap.appointment.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrowException(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

}
