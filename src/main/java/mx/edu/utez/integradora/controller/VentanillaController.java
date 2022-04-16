package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Cita;
import mx.edu.utez.integradora.model.HorarioCita;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.CitaServiceImpl;
import mx.edu.utez.integradora.service.impl.HorarioCitaServiceImpl;
import mx.edu.utez.integradora.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/ventanilla")
public class VentanillaController {
    @Autowired
    CitaServiceImpl citaService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private HorarioCitaServiceImpl horarioCitaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/home")
    public String home(Model model, Authentication authentication, HttpSession session) {
        User user = userService.buscarCorreo(authentication.getName());
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        return "ventanilla/homeVentanilla";
    }

    @PostMapping(path = "/cambiarContra")
    public String guardarCambios(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for(ObjectError error: result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }

            return "ventanilla/homeVentanilla";
        }
        User userExistente = userService.mostrar(user.getId());
        if (userExistente == null) {
            return "redirect:/ventanilla/home";
        } else {
            String contrar = user.getContrasenia();
            String contraEncrip = passwordEncoder.encode(contrar);
            boolean respuestaCambio = userService.cambiarContrasena(contraEncrip, userExistente.getCorreo());
            if (respuestaCambio) {
                attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");
                return "redirect:/ventanilla/home";
            } else {
                attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
                return "redirect:/ventanilla/home";
            }
        }
    }

    @PostMapping(path = "/cambiarInfo")
    public String cambiarInfo(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for(ObjectError error: result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }

            return "ventanilla/homeVentanilla";
        }
        User userExistente = userService.mostrar(user.getId());
        userExistente.setCorreo(user.getCorreo());
        userExistente.setNombre(user.getNombre());
        userExistente.setApellidos(user.getApellidos());
        if (userExistente == null) {
            return "redirect:/ventanilla/home";
        } else {
            boolean respuestaCambio = userService.crearUser(userExistente);
            if (respuestaCambio) {
                attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");
                return "redirect:/ventanilla/home";
            } else {
                attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
                return "redirect:/ventanilla/home";
            }
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, null, null);
			redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
        } catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg_error","Ocurrió un error al cerrar la sesión, intenta de nuevo.");
        }
        return "/login";
    }

    @GetMapping("/consultarCitas")
    public String gestionarCitas(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6, Sort.by("fecha").descending()));
        model.addAttribute("listaCitas", listaCitas);
        return "ventanilla/consultarCitas";
    }

    @GetMapping(path = "/cancelar/{id}")
    public String cancelarCita(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Cita respuesta = citaService.mostrar(id);
        respuesta.setEstatus("cancelada");
        citaService.crearCita(respuesta);
        Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6));
        model.addAttribute("listaCitas", listaCitas);
        return "redirect:/ventanilla/consultarCitas";
    }

    @GetMapping(path = "/finalizar/{id}")
    public String finalizarCita(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Cita respuesta = citaService.mostrar(id);
        respuesta.setEstatus("finalizada");
        citaService.crearCita(respuesta);
        Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6));
        model.addAttribute("listaCitas", listaCitas);
        return "redirect:/ventanilla/consultarCitas";
    }


    @GetMapping(path = "/consultarHorarios")
    public String consultarHorarios(Model model, RedirectAttributes redirectAttributes, Pageable pageable, Authentication auth) {
        String username=auth.getName();
        User usuario=userService.buscarCorreo(username);
        long idSesion=usuario.getId();
        //Por el momento dejarlo así no sé por que me esta protestando por el id
        model.addAttribute("listHorarios", horarioCitaService.listarTodos());
        return "ventanilla/listHorarios";
    }

    @GetMapping(path = "/formHorario")
    public String gestionHorario(HorarioCita horarioCita, Model model) {
        return "ventanilla/formHorario";
    }

    @PostMapping(path = "/guardarHorario")
    public String guardarHorario(
            @RequestParam(name = "numRepeticiones", required = true) int numRepeticiones,
            @RequestParam(name = "fecha", required = true, defaultValue = "#{ new java.util.Date() }") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date fecha,
            @RequestParam(name = "horaInicio", required = true, defaultValue = "#{ new java.util.Date() }") @DateTimeFormat(pattern = "HH:mm") Date horaInicio,
            @RequestParam(name = "horaFin", required = true, defaultValue = "#{ new java.util.Date() }") @DateTimeFormat(pattern = "HH:mm") Date horaFin,
            @RequestParam(name = "numVentanilla", required = true) int numV,
            Authentication auth, HorarioCita horarioCita) {
        String username = auth.getName();
        User userExist = userService.buscarCorreo(username);
        long idSesion = userExist.getId();
        horarioCitaService.guardarHorario(fecha, horaInicio, horaFin, numV, numRepeticiones, idSesion);
        return "redirect:/ventanilla/consultarHorarios";
    }
}