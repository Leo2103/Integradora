package mx.edu.utez.integradora.service;

import mx.edu.utez.integradora.model.Servicio;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServicioService {
    List<Servicio> listarTodos();
    Servicio mostrar(long id);
    boolean crearServicio(Servicio servicio);
    boolean eliminarServicio(long id);
    Page<Servicio> listarPaginacion(Pageable page);
}
