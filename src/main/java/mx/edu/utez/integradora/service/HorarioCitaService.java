package mx.edu.utez.integradora.service;

import java.util.Date;
import java.util.List;

import mx.edu.utez.integradora.model.HorarioCita;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioCitaService {
	List<HorarioCita> listarTodos();
}
