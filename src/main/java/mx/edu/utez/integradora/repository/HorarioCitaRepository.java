package mx.edu.utez.integradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.integradora.model.HorarioCita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HorarioCitaRepository extends JpaRepository<HorarioCita, Long> {

    @Query(value = "select * from horario_ventanilla order by id_horario_ventanilla desc limit 1", nativeQuery = true)
    HorarioCita consultarUltimo();

    @Procedure(procedureName = "registroHorario")
    void registroHorario(
            Date fechaIn,
            Date horaInicioIn,
            Date horaFinIn ,
            int numVentanillaIn ,
            int repeticionesIn,
            long usuario );
}
