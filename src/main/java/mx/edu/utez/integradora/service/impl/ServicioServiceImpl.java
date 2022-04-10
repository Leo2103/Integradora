package mx.edu.utez.integradora.service.impl;

import mx.edu.utez.integradora.model.Servicio;
import mx.edu.utez.integradora.repository.ServicioRepository;
import mx.edu.utez.integradora.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    ServicioRepository repository;
    
    @Override
    public List<Servicio> listarTodos() {
        return repository.findAll();
    }

    @Override
    public boolean crearServicio(Servicio servicio) {
        try {
            repository.save(servicio);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarServicio(long id) {
        boolean exist= repository.existsById(id);
        if (exist){
            repository.deleteById(id);
            return !repository.existsById(id);
        }
        return false;
    }

    @Override
    public Page<Servicio> listarPaginacion(Pageable page) {
        return repository.findAll(page);
    }

    @Override
	public Servicio mostrar(long id){
		Optional<Servicio> obj = repository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
            return null;
	}
	

}
