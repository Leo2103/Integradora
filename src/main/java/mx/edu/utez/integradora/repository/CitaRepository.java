package mx.edu.utez.integradora.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.integradora.model.Cita;
import org.springframework.stereotype.Repository;


@Repository
public interface CitaRepository extends JpaRepository<Cita,Long>{

}
