package mx.edu.utez.Integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/home")
public class SolicitanteController {
	
	@GetMapping("/")
	public String iniciar() {
		return "home";
	}
	
	@GetMapping("/crear")
	public String registrarSolicitante() {
		return "solicitante/formSolicitante";
	}
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/usuarios")
	public String gestionarUsuarios() {
		return "administrador/listUsuarios";
	}
}
