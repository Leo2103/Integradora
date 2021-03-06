package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Role;
import mx.edu.utez.integradora.model.Servicio;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    static final String CONSULTARUSUARIOS = "redirect:/administrador/consultarUsuarios";
    static final String CONSULTARSERVICIOS = "redirect:/administrador/consultarServicios";

    @GetMapping(path = "/home")
    public String home(Model model, Authentication authentication, HttpSession session) {
        User user = userService.buscarCorreo(authentication.getName());
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        return "/administrador/adminHome";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, null, null);
            redirectAttributes.addFlashAttribute("msg_success", "??Sesi??n cerrada! Hasta luego");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Ocurri?? un error al cerrar la sesi??n, intenta de nuevo.");
        }
        return "/login";
    }

    @GetMapping(path = "/consultarUsuarios")
    public String gestionarUsuarios(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<User> listaUsuarios = userService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 5));
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "administrador/listUsuarios";
    }

    @PostMapping(path = "/cambiarContra")
    public String guardarCambios(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
        }
        User userExistente = userService.mostrar(user.getId());
        String contrar = user.getContrasenia();
        String contraEncrip = passwordEncoder.encode(contrar);
        boolean respuestaCambio = userService.cambiarContrasena(contraEncrip, userExistente.getCorreo());
        if (respuestaCambio) {
            attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");
        } else {
            attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
        }
            return "redirect:/administrador/home";
    }

    @PostMapping(path = "/cambiarInfo")
    public String cambiarInfo(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
        }
        User userExistente = userService.mostrar(user.getId());
        userExistente.setCorreo(user.getCorreo());
        userExistente.setNombre(user.getNombre());
        userExistente.setApellidos(user.getApellidos());
        boolean respuestaCambio = userService.crearUser(userExistente);
        if (respuestaCambio) {
            attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");

        } else {
            attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
        }
        return "redirect:/administrador/home";

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

    @PostMapping(path = "/eliminarUsuario")
    public String eliminarUsuario(User user, RedirectAttributes redirectAttributes) {
        User userExist = userService.mostrar(user.getId());
        boolean respuesta = userService.eliminarUser(userExist.getId());
        if (respuesta) {
            redirectAttributes.addFlashAttribute("msg_success", "Eliminacion exitosa");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Eliminacion fallida");
        }
        return CONSULTARUSUARIOS;
    }

    @GetMapping(path = "/editarUsuario/{id}")
    public String editarUsuario(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        User user = userService.mostrar(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "administrador/formUsuario";
        }
        return CONSULTARUSUARIOS;
    }

    @GetMapping(path = "/mostrarUsuario/{id}")
    public String mostrarUsuario(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        User user = userService.mostrar(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "administrador/mostrarServicio";
        }
        return CONSULTARSERVICIOS;
    }

    @PostMapping("/guardarUser")
    public String guardarUser(@RequestParam("tipoUsuario") String tipoUsuario, @Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
        }
        Role rol = null;
        if (tipoUsuario.equals("opcionAdministrador")) {
            rol = roleService.buscarAuthority("ROLE_ADMIN");
        } else if (tipoUsuario.equals("opcionVentanilla")) {
            rol = roleService.buscarAuthority("ROLE_VENTANILLA");
        } else {
            rol = roleService.buscarAuthority("ROLE_USER");
        }
        user.agregarRole(rol);
        user.setContrasenia(passwordEncoder.encode(user.getContrasenia()));
        boolean respuesta = userService.crearUser(user);
        if (respuesta) {
            attributes.addFlashAttribute("msg_success", "Registro exitoso");
        } else {
            attributes.addFlashAttribute("msg_error", "Registro fallido");
        }
        return CONSULTARUSUARIOS;
    }

    @PostMapping(path = "/guardarServicio")
    public String guadarServicio(@Valid @ModelAttribute("servicio") Servicio servicio, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
        }
        boolean respuesta = servicioService.crearServicio(servicio);
        if (respuesta) {
            attributes.addFlashAttribute("msg_success", "Registro exitoso");
        } else {

            attributes.addFlashAttribute("msg_error", "Registro fallido");
        }
        return CONSULTARSERVICIOS;
    }

    @PostMapping(path = "/eliminarServicio")
    public String eliminarServicio(Servicio servicio, RedirectAttributes redirectAttributes) {
        Servicio servicioExist = servicioService.mostrar(servicio.getId());
        boolean respuesta = servicioService.eliminarServicio(servicioExist.getId());
        if (respuesta) {
            redirectAttributes.addFlashAttribute("msg_success", "Eliminacion exitosa");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Eliminacion fallida");
        }
        return CONSULTARSERVICIOS;
    }


    @GetMapping(path = "/mostrarServicio/{id}")
    public String mostrarServicio(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.mostrar(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
        }
        return CONSULTARSERVICIOS;
    }

    @GetMapping("/editarServicio/{id}")
    public String editarServicio(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.mostrar(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
        }
        return CONSULTARSERVICIOS;
    }

}