package mx.edu.utez.integradora.repository;

import mx.edu.utez.integradora.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreo(String user);
    @Modifying
    @Query(value = "update users u set u.contrasenia = :password where u.correo = :username", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("username") String username);
}
