package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.SolicitanteServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    SolicitanteServiceImpl solicitanteService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/")
    public String iniciar() {
        return "home";
    }

    //este lo veo mas a nivel de home es decir mover la vista a un nivel general
    @GetMapping(path = "/registrar")
    public String registrarSolicitante(Solicitante solicitante, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {

            for(ObjectError error: result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            attributes.addFlashAttribute("msg_error", "Registro fallido");
            return "redirect:/administrador/formServicio";
        }
        boolean respuesta = solicitanteService.crearSolicitante(solicitante);
        if (respuesta) {
            attributes.addFlashAttribute("msg_success", "Registro exitoso");
            return "redirect:/administrador/consultarServicios";
        } else {
            return "redirect:/administrador/formServicio";
        }
    }

    @GetMapping(path = "/login")
    public String iniciarSesion() {
        return "login";
    }

    @PostMapping(path = "/guardarSolicitante")
    public String guardarSolicitante() {
        return "";
    }
    @GetMapping("/encriptar/{contrasena}")
    @ResponseBody
    public String encriptarContrasenas(@PathVariable("contrasena") String contrasena) {
        return contrasena + " encriptada con el algoritmo bcrypt: " + passwordEncoder.encode(contrasena);
    }

}