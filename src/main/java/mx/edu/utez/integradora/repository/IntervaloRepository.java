package mx.edu.utez.integradora.repository;

import mx.edu.utez.integradora.model.Intervalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervaloRepository extends JpaRepository<Intervalo,Long> {
    List<Intervalo> findByEnableTrue();
}
