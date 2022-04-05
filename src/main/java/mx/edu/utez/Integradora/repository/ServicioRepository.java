package mx.edu.utez.Integradora.repository;

import mx.edu.utez.Integradora.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio,Long>{
    Optional<Servicio> findByNombre(Servicio nombre);
}
