package mx.edu.utez.integradora.repository;

import mx.edu.utez.integradora.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
