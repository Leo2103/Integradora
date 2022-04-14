package mx.edu.utez.integradora.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.integradora.model.HorarioCita;
import mx.edu.utez.integradora.repository.HorarioCitaRepository;
import mx.edu.utez.integradora.service.HorarioCitaService;

@Service
public class HorarioCitaServiceImpl implements HorarioCitaService{
    @Autowired
    HorarioCitaRepository repository;
    
	@Override
	public List<HorarioCita> listarTodos() {
		return repository.findAll();
	}

	@Override
	public List<HorarioCita> listarByUser(Long id) {
		return repository.findByUser(id);
	}

	@Override
	public boolean guardar(HorarioCita horarioCita) {
		try{
			repository.save(horarioCita);
			return true;
		}catch (Exception e){
			repository.save(horarioCita);
		return false;
		}
	}

	@Override
	public boolean guardarHorario(Date fecha_in, Date horaInicio_in, Date horaFin_in, int numVentanilla_in, int repeticiones_in, int usuario) {
		return false;
	}

}