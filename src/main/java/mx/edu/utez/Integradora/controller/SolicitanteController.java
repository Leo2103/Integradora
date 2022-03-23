package mx.edu.utez.Integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/solicitante")
public class SolicitanteController {
	
	@GetMapping("")
	public String iniciar() {
		return "solicitante/homeSolicitante";
	}
	
	@GetMapping("/listCitas")
	public String listarCitas() {
		return "solicitante/listCitas";
	}
	
	@GetMapping("/agendar1")
	public String agendar1() {
		return "solicitante/agendar1";
	}
	
	@GetMapping("/agendar2")
	public String agendar2() {
		return "solicitante/agendar2";
	}
	@GetMapping("/agendar3")
	public String agendar3() {
		return "solicitante/agendar3";
	}
	
}
