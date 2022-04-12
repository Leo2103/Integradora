package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Cita;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.CitaServiceImpl;
import mx.edu.utez.integradora.service.impl.HorarioCitaImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/solicitante")
public class SolicitanteController {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private HorarioCitaImpl HorarioCitaSercivesImplement;
	
	 @Autowired
		CitaServiceImpl citaService;
	
	@GetMapping(path = "/home")
	public String home(Authentication authentication, HttpSession session) {
		if (session.getAttribute("user")==null){
			User user= userService.buscarCorreo(authentication.getName());
			user.setContrasenia(null);
			session.setAttribute("user", user);
		}	return "solicitante/homeSolicitante";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.logout(request, null, null);
			//redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
		} catch (Exception e) {
			//redirectAttributes.addFlashAttribute("msg_error","Ocurrió un error al cerrar la sesión, intenta de nuevo.");
		}
		return "/login";
	}

	@GetMapping(path = "/cancelar/{id}")
    public String cancelarCita(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Cita respuesta = citaService.mostrar(id);
        respuesta.setEstatus("cancelada");
        citaService.crearCita(respuesta);
		Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6));
        model.addAttribute("listaCitas", listaCitas);
		return "redirect:/solicitante/listCitas";
    }

	@GetMapping("/listCitas")
	public String listarCitas(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
		Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6, Sort.by("fecha").descending()));
        model.addAttribute("listaCitas", listaCitas);
		return "solicitante/listCitas";
    }
	
	@GetMapping("/agendar1")
	public String agendar1(Model model) {
		model.addAttribute("listaHorariosCitas" ,HorarioCitaSercivesImplement.listarTodos());
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
