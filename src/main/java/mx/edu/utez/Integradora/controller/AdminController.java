package mx.edu.utez.Integradora.controller;

import mx.edu.utez.Integradora.model.Servicio;
import mx.edu.utez.Integradora.service.impl.ServicioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ServicioServiceImpl servicioService;

    @GetMapping("/home")
    public String home() {
        return "administrador/AdminHome";
    }

    @GetMapping("/consultarUsuarios")
    public String gestionarUsuarios() {
        return "administrador/listUsuarios";
    }

    @GetMapping("/consultarServicios")
    public String gestionarServicios(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<Servicio> listaServicios = servicioService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 10));
        model.addAttribute("listaServicios", listaServicios);
        return "administrador/listServicio";
    }

    @GetMapping("/formServicio")
    public String crearServicio(Servicio servicio) {
        return "administrador/formServicio";
    }

    @GetMapping("/formUsuario")
    public String crearUsuario() {
        return "administrador/formUsuario";
    }

    @PostMapping("/guardar")
    public String guadarServicio(Servicio servicio, Model model, RedirectAttributes attributes) {
        boolean respuesta = servicioService.crearServicio(servicio);
        if (respuesta) {
            attributes.addFlashAttribute("msg_success", "Registro exitoso");
            return "redirect:/admin/consultarServicios";
        } else {
            attributes.addFlashAttribute("msg_error", "Registro fallido");
            return "redirect:/admin/formServicio";
        }
    }

    @GetMapping("/panelAdm")
    public String panelAdmin() {
        return "administrador/panelAdministrador";
    }

    @GetMapping("/gestionHora")
    public String gestionHorario() {
        return "administrador/gestionarHorario";
    }
}
