package mx.edu.utez.integradora.service;

import java.util.Date;
import java.util.List;

import mx.edu.utez.integradora.model.HorarioCita;

public interface HorarioCitaService {
	List<HorarioCita> listarTodos();
	List<HorarioCita> listarByUser(Long id);
	boolean guardar(HorarioCita horarioCita);

	HorarioCita consultarHorario();

	void guardarHorario(Date fecha_in, Date horaInicio_in , Date horaFin_in , int numVentanilla_in , int repeticiones_in, long usuario );

}
