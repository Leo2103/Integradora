package mx.edu.utez.integradora.service;

import mx.edu.utez.integradora.model.Intervalo;

import java.util.List;

public interface IntervaloService {
    List<Intervalo> horarioDisponible();
    Intervalo mostrar(long id);
}
