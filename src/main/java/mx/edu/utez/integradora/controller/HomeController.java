package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Role;
import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.RoleServiceImpl;
import mx.edu.utez.integradora.service.impl.SolicitanteServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    SolicitanteServiceImpl solicitanteService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping(path = "/")
    public String iniciar() {
        return "home";
    }

    @GetMapping(path = "/registrar")
    public String registrarSolicitante(User user) {
        return "formSolicitante";
    }

    @GetMapping(path = "/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, null, null);
            redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al cerrar la sesión, intenta de nuevo.");
        }
        return "/login";
    }

    @GetMapping("/encriptar/{contrasena}")
    @ResponseBody
    public String encriptarContrasenas(@PathVariable("contrasena") String contrasena) {
        return contrasena + " encriptada con el algoritmo bcrypt: " + passwordEncoder.encode(contrasena);
    }

    @PostMapping("/guardarSolicitante")
    public String guardarSolicitante(@RequestParam("matricula") String matricula,
                                     @RequestParam("telefono") String telefono,
                                     @RequestParam("carrera") String carrera,
                                     @Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {
        Role rol = null;

        if(result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for(ObjectError error: result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
        }
        user.setEnabled(true);
        user.setContrasenia(passwordEncoder.encode(user.getContrasenia()));
        rol = roleService.buscarAuthority("ROLE_USER");
        user.agregarRole(rol);

        Boolean respuesta = userService.crearUser(user);
        if (respuesta != null) {
            User registrado = userService.buscarCorreo(user.getCorreo());
            Solicitante solicitante = new Solicitante();
            solicitante.setUsuario(registrado);
            solicitante.setCarrera(carrera);
            solicitante.setMatricula(matricula);
            solicitante.setTelefono(telefono);
            solicitante.setUsuario(user);
            boolean r = solicitanteService.crearSolicitante(solicitante);
            if (respuesta != null) {
                attributes.addFlashAttribute("msg_success", "Se ha registrado de manera exitosa");
            } else {
                attributes.addFlashAttribute("msg_success", "Registro erroneo");
            }
        }
            return "redirect:/login";
    }
}