package mx.edu.utez.integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventanilla")
public class VentanillaController {
	
	@GetMapping("/citas")
	public String gestionarUsuarios() {
		return "ventanilla/gestionarCita";
	}

	@GetMapping(path = "/home")
	public String home(){
		return "";
	}

}
