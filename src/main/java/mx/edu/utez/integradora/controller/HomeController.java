package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.SolicitanteServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping(path = "/crear")
    public String registrarSolicitante(Solicitante solicitante, User user) {
        return "solicitante/formSolicitante";
    }

    @GetMapping(path = "/login")
    public String iniciarSesion() {
        return "login";
    }

    @PostMapping(path = "/guardarUser")
    public String guardarSolicitante() {
        return "";
    }
    @GetMapping("/encriptar/{contrasena}")
    @ResponseBody
    public String encriptarContrasenas(@PathVariable("contrasena") String contrasena) {
        return contrasena + " encriptada con el algoritmo bcrypt: " + passwordEncoder.encode(contrasena);
    }

}