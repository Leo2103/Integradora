package mx.edu.utez.Integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/solicitante")
public class SolicitanteController {
	
	@GetMapping("/home")
	public String iniciar() {
		return "home";
	}
	
	@GetMapping("/crear")
	public String crearMascota() {
		return "solicitante/formSolicitante";
	}
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	

}
