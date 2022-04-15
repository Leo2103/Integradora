package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Role;
import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.RoleServiceImpl;
import mx.edu.utez.integradora.service.impl.SolicitanteServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

        if (result.hasErrors()) {

            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }

        }
        user.setEnabled(1);
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
                System.out.println("Solicitante no almacenado");
                attributes.addFlashAttribute("msg_success", "Registro erroneo");
            }
        }
            return "redirect:/login";
    }
}