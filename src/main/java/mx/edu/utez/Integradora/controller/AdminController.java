package mx.edu.utez.Integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/home")
    public String home(){
        return "administrador/AdminHome";
    }
    @GetMapping("/consultarUsuarios")
    public String gestionarUsuarios() {
        return "administrador/listUsuarios";
    }

    @GetMapping("/consultarServicios")
    public String gestionarServicios(){
        return "administrador/listServicio";
    }
    @GetMapping("/formServicio")
    public String crearServicio(){
        return "administrador/formServicio";
    }
}
