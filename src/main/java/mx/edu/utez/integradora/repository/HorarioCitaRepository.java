package mx.edu.utez.integradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.integradora.model.HorarioCita;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface HorarioCitaRepository extends JpaRepository<HorarioCita, Long> {
    List<HorarioCita> findByUser(Long id);
    @Procedure(procedureName = "registroHorario")
    boolean registroHorario(String fecha_in, String horaInicio_in , String horaFin_in , int numVentanilla_in , int repeticiones_in, int usuario );
}
