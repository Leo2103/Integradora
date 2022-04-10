package mx.edu.utez.integradora.service;

import mx.edu.utez.integradora.model.Solicitante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SolicitanteService {
    Page<Solicitante> listarPaginacion(Pageable pageable);
    List<Solicitante> listarTodos();
    Solicitante mostrar(long id);
    boolean crearSolicitante(Solicitante solicitante);
    boolean eliminarSolicitante(long id);

}
