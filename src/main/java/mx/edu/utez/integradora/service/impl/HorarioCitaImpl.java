package mx.edu.utez.integradora.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.integradora.model.HorarioCita;
import mx.edu.utez.integradora.repository.HorarioCitaRepository;
import mx.edu.utez.integradora.service.HorarioCitaService;

@Service
public class HorarioCitaImpl implements HorarioCitaService{
    @Autowired
    HorarioCitaRepository repository;
    
	@Override
	public List<HorarioCita> listarTodos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}