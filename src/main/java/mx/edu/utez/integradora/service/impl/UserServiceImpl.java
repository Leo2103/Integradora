package mx.edu.utez.integradora.service.impl;

import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.repository.UserRepository;
import mx.edu.utez.integradora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> listarTodos() {
        return repository.findAll();
    }

    @Override
    public boolean crearUser(User user) {
        try {
            repository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean cambiarContrasena(String password, String username) {
        try {
        	repository.updatePassword(password, username);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarUser(long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public User mostrar(long id) {
        Optional<User> obj = repository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;    }

    @Override
    public Page<User> listarPaginacion(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public User buscarCorreo(String user) {
        return repository.findByCorreo(user);
    }
}