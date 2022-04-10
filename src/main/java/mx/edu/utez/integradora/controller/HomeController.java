package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.service.impl.SolicitanteServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@Autowired
	SolicitanteServiceImpl solicitanteService;
	@Autowired
	UserServiceImpl userService;
	@GetMapping(path = "/")
	public String iniciar() {
		return "home";
	}
	//este lo veo mas a nivel de home es decir mover la vista a un nivel general
	@GetMapping(path = "/crear")
	public String registrarSolicitante() {
		return "solicitante/formSolicitante";
	}

	@GetMapping(path = "/login")
	public String iniciarSesion() {
		return "login";
	}
	

}