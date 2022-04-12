package mx.edu.utez.integradora.repository;

import mx.edu.utez.integradora.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long>{
    Optional<Servicio> findByNombre(Servicio nombre);
}
