package mx.edu.utez.integradora.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mx.edu.utez.integradora.model.Cita;
import mx.edu.utez.integradora.repository.CitaRepository;
import mx.edu.utez.integradora.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService {

	@Autowired
    CitaRepository repository;
    
    @Override
    public List<Cita> listarTodos() {
        return repository.findAll(Sort.by("fecha"));
    }

    @Override
    public boolean crearCita(Cita cita) {
        try {
            repository.save(cita);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCita(long id) {
        boolean exist= repository.existsById(id);
        if (exist){
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public Page<Cita> listarPaginacion(Pageable page) {
        return repository.findAll(page);
    }

    @Override
	public Cita mostrar(long id){
		Optional<Cita> obj = repository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
            return null;
	}
}

