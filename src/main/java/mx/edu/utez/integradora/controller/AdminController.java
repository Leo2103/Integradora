package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Role;
import mx.edu.utez.integradora.model.Servicio;
import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.RoleServiceImpl;
import mx.edu.utez.integradora.service.impl.ServicioServiceImpl;
import mx.edu.utez.integradora.service.impl.SolicitanteServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/administrador")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ServicioServiceImpl servicioService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private SolicitanteServiceImpl solicitanteService;

    @GetMapping(path = "/home")
    public String home(Authentication authentication, HttpSession session) {
        if (session.getAttribute("user") == null) {
            User user = userService.buscarCorreo(authentication.getName());
            user.setContrasenia(null);
            session.setAttribute("user", user);
        }
        return "/administrador/adminHome";
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
    public String crearServicio(Servicio servicio) {
        return "administrador/formServicio";
    }

    @GetMapping(path = "/formUsuario")
    public String crearUsuario(User user) {
        return "administrador/formUsuario";
    }

    @PostMapping(path = "/guardarUser")
    public String guardarUser(@RequestParam("tipoUsuario") String tipoUsuario, User user, Solicitante solicitante, RedirectAttributes attributes) {
        String contra = user.getContrasenia();
        String contraEncrip = passwordEncoder.encode(contra);
        user.setContrasenia(contraEncrip);
        user.setEnabled(true);
        user.setCorreo(user.getCorreo());
        Role role = null;
        if (tipoUsuario.equals("opcionAdministrador")) {
            role = roleService.buscarAuthority("ROLE_ADMIN");
        } else if (tipoUsuario.equals("opcionVentanilla")) {
            role = roleService.buscarAuthority("ROLE_VENTANILLA");
        } else {
            role = roleService.buscarAuthority("ROLE_USER");
        }
        user.agregarRole(role);
        boolean respuesta = userService.crearUser(user);
        if (respuesta) {
            if (tipoUsuario.equals("opciónUsuario")) {
                userService.crearUser(user);
                return "usuario/form";
            } else {
                attributes.addFlashAttribute("msg_success", "Registro exitoso");
                return "administrador/listUsuarios";
            }
        } else {
            attributes.addFlashAttribute("msg_error", "Registro fallido");
            return "redirect:/administrador/formUsuario";
        }
    }

    @PostMapping(path = "/guardarServicio")
    public String guadarServicio(Servicio servicio, Model model, RedirectAttributes attributes) {
        boolean respuesta = servicioService.crearServicio(servicio);
        if (respuesta) {
            attributes.addFlashAttribute("msg_success", "Registro exitoso");
            return "redirect:/administrador/consultarServicios";
        } else {
            attributes.addFlashAttribute("msg_error", "Registro fallido");
            return "redirect:/administrador/formServicio";
        }
    }

    @GetMapping(path = "/eliminarServicio/{id}")
    public String eliminarServicio(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        boolean respuesta = servicioService.eliminarServicio(id);
        if (respuesta) {
            redirectAttributes.addFlashAttribute("msg_success", "Eliminacion exitosa");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Eliminacion fallida");
        }
        return "redirect:/administrador/consultarServicios";
    }


    @GetMapping(path = "/mostrarServicio/{id}")
    public String mostrarServicio(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.mostrar(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "administrador/mostrarServicio";
        }
        return "redirect:/administrador/consultarServicios";
    }

    @GetMapping("/editarServicio/{id}")
    public String editarServicio(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.mostrar(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "administrador/formServicio";
        }
        return "redirect:/administrador/consultarServicios";
    }

}