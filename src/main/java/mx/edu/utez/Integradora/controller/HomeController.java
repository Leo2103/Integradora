package mx.edu.utez.Integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@GetMapping("/")
	public String iniciar() {
		return "home";
	}
	//este lo veo mas a nivel de home es decir mover la vista a un nivel general
	@GetMapping("/crear")
	public String registrarSolicitante() {
		return "solicitante/formSolicitante";
	}
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	

}