package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ventanilla")
public class VentanillaController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping(path = "/home")
	public String home(Authentication authentication, HttpSession session) {
		if (session.getAttribute("user") == null) {
			User user = userService.buscarCorreo(authentication.getName());
			user.setContrasenia(null);
			session.setAttribute("user", user);
		}
		return "ventanilla/homeVentanilla";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.logout(request, null, null);
//			redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("msg_error","Ocurrió un error al cerrar la sesión, intenta de nuevo.");
		}
		return "/login";
	}
	@GetMapping("/citas")
	public String gestionarUsuarios() {
		return "ventanilla/gestionarCita";
	}
	@GetMapping(path = "/gestionHora")
	public String gestionHorario() {
		return "ventanilla/gestionarHorario";
	}

}
