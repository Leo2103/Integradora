package mx.edu.utez.integradora.repository;

import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
	Optional<Solicitante> findByUsuario(User usuario);
}
