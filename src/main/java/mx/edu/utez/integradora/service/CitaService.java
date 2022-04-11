package mx.edu.utez.integradora.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.edu.utez.integradora.model.Cita;

public interface CitaService {
	 List<Cita> listarTodos();
	 Cita mostrar(long id);
	    boolean crearCita(Cita cita);
	    boolean eliminarCita(long id);
	    Page<Cita> listarPaginacion(Pageable page);
}
