package mx.edu.utez.integradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.integradora.model.HorarioCita;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;

@Repository
public interface HorarioCitaRepository extends JpaRepository<HorarioCita, Long> {
    @Procedure(procedureName = "registroHorario")
    HorarioCita registroHorario(Date fecha_in, Time horaInicio_in , Time horaFin_in , int numVentanilla_in , int repeticiones_in );
}
