package mx.edu.utez.integradora.repository;

import mx.edu.utez.integradora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreo(String user);
}
