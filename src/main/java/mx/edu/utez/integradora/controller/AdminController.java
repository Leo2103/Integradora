package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Servicio;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.ServicioServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ServicioServiceImpl servicioService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping(path = "/home")
    public String home() {
        return "adminHome";
    }

    @GetMapping(path = "/consultarUsuarios")
    public String gestionarUsuarios(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<User> listaUsuarios = userService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 10));
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "administrador/listUsuarios";
    }

    @GetMapping(path = "/consultarServicios")
    public String gestionarServicios(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<Servicio> listaServicios = servicioService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 10));
        model.addAttribute("listaServicios", listaServicios);
        return "administrador/listServicio";
    }

    @GetMapping(path = "/formServicio")
    public String crearServicio() {
        return "administrador/formServicio";
    }

    @GetMapping(path = "/formUsuario")
    public String crearUsuario() {
        return "administrador/formUsuario";
    }

    @PostMapping(path = "/guardarUser")
    public String guardarUser(User user, RedirectAttributes attributes) {
        boolean respuesta = userService.crearUser(user);
        if (respuesta) {
            attributes.addFlashAttribute("msg_success", "Registro exitoso");
            return "administrador/listUsuarios";
        } else {
            attributes.addFlashAttribute("msg_error", "Registro fallido");
            return "redirect:/admin/formUsuario";
        }
    }

    @PostMapping(path = "/guardarServicio")
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

    @GetMapping(path = "/panelAdm")
    public String panelAdmin() {
        return "administrador/panelAdministrador";
    }

    @GetMapping(path = "/gestionHora")
    public String gestionHorario() {
        return "administrador/gestionarHorario";
    }

    @DeleteMapping(path = "/eliminar/{id}")
    public String eliminarServicio(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        boolean respuesta = servicioService.eliminarServicio(id);
        if (respuesta) {
            redirectAttributes.addFlashAttribute("msg_success", "Eliminacion exitosa");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Eliminacion fallida");
        }
        return "redirect:/admin/consultarServicios";
    }

    @GetMapping(path = "/mostrar/{id}")
    public String mostrarServicio(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.mostrar(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "administrador/showServicio";
        }
        return "redirect:/admin/consultarServicios";
    }

    @GetMapping("/editar/{id}")
    public String editarServicio(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.mostrar(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "administrador/formServicio";
        }
        return "redirect:/admin/consultarServicios";
    }

}
