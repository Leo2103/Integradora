package mx.edu.utez.integradora.service.impl;

import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.repository.SolicitanteRepository;
import mx.edu.utez.integradora.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SolicitanteServiceImpl implements SolicitanteService {
    @Autowired
    SolicitanteRepository repository;

    @Override
    public Page<Solicitante> listarPaginacion(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<Solicitante> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Solicitante mostrar(long id) {
        Optional<Solicitante> obj = repository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
    }

    @Override
    public boolean crearSolicitante(Solicitante solicitante) {
        try {
            repository.save(solicitante);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarSolicitante(long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }
}
