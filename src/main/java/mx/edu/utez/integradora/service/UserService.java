package mx.edu.utez.integradora.service;

import mx.edu.utez.integradora.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> listarTodos();
    boolean crearUser(User user);
    boolean eliminarUser(long id);
    User mostrar(long id);
    Page<User> listarPaginacion(Pageable page);
    User buscarCorreo(String user);
}
