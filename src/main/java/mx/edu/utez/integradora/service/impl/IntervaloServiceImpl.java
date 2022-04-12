package mx.edu.utez.integradora.service.impl;

import mx.edu.utez.integradora.model.Cita;
import mx.edu.utez.integradora.model.Intervalo;
import mx.edu.utez.integradora.repository.IntervaloRepository;
import mx.edu.utez.integradora.service.IntervaloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntervaloServiceImpl implements IntervaloService {
    @Autowired
    IntervaloRepository repository;
    @Override
    public List<Intervalo> horarioDisponible() {
        return repository.findByEnableTrue();
    }

    @Override
    public Intervalo mostrar(long id) {
        Optional<Intervalo> obj = repository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }
}
