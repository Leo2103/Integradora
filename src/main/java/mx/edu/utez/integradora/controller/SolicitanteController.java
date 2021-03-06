package mx.edu.utez.integradora.controller;

import mx.edu.utez.integradora.model.Cita;
import mx.edu.utez.integradora.model.Solicitante;
import mx.edu.utez.integradora.model.User;
import mx.edu.utez.integradora.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/solicitante")
public class SolicitanteController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SolicitanteServiceImpl solicitanteService;
    @Autowired
    private HorarioCitaServiceImpl horarioCitaService;
    @Autowired
    CitaServiceImpl citaService;
    @Autowired
    private ServicioServiceImpl servicioService;
    @Autowired
    private IntervaloServiceImpl intervaloService;

    @GetMapping(path = "/home")
    public String home(Model model, Authentication authentication, HttpSession session) {
        User user = userService.buscarCorreo(authentication.getName());
        session.setAttribute("user", user);
        model.addAttribute("user", user);

        return "solicitante/homeSolicitante";
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

    @GetMapping(path = "/cancelar/{id}")
    public String cancelarCita(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Cita respuesta = citaService.mostrar(id);
        respuesta.setEstatus("cancelada");
        citaService.crearCita(respuesta);
        Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6));
        model.addAttribute("listaCitas", listaCitas);
        return "redirect:/solicitante/listCitas";
    }

    @PostMapping(path = "/cambiarInfo")
    public String cambiarInfo(@Valid @ModelAttribute("user") User user, Model model, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            return "solicitante/homeSolicitante";
        }
        User userExistente = userService.mostrar(user.getId());
        userExistente.setCorreo(user.getCorreo());
        userExistente.setNombre(user.getNombre());
        userExistente.setApellidos(user.getApellidos());
        if (userExistente == null) {
            attributes.addFlashAttribute("msg_error", "Rehistro no encontrado");
        } else {
            boolean respuestaCambio = userService.crearUser(userExistente);
            if (respuestaCambio) {
                attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");

            } else {
                attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
            }
        }
        return "redirect:/solicitante/home";
    }


    @PostMapping(path = "/cambiarInfo2")
    public String cambiarInfo2(@Valid @ModelAttribute("solicitante") Solicitante solicitante, Model model, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
        }
        Solicitante soliExistente = solicitanteService.mostrar(solicitante.getId());
        soliExistente.setCarrera(solicitante.getCarrera());
        soliExistente.setMatricula(solicitante.getMatricula());
        soliExistente.setTelefono(solicitante.getTelefono());
        if (soliExistente == null) {
        } else {
            boolean respuestaCambio = solicitanteService.crearSolicitante(soliExistente);
            if (respuestaCambio) {
                attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");
            } else {
                attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
            }
        }
        return "redirect:/solicitante/home";
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

    @GetMapping("/listCitas")
    public String listarCitas(Model model, RedirectAttributes redirectAttributes, Pageable pageable) {
        Page<Cita> listaCitas = citaService.listarPaginacion(PageRequest.of(pageable.getPageNumber(), 6, Sort.by("fecha").descending()));
        model.addAttribute("listaCitas", listaCitas);
        return "solicitante/listCitas";
    }

    @PostMapping(path = "/cambiarContra")
    public String guardarCambios(@Valid @ModelAttribute("user") User user, Model model, BindingResult result, RedirectAttributes attributes, Authentication authentication, HttpSession session) {
        if (result.hasErrors()) {
            List<String> errores = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            User user2 = userService.buscarCorreo(authentication.getName());
            session.setAttribute("user", user2);
            Solicitante solicitante = solicitanteService.buscarporUser(user2);
            model.addAttribute("user", user2);
            model.addAttribute("solicitante", solicitante);
        }
        User userExistente = userService.mostrar(user.getId());
        if (userExistente == null) {
        } else {
            String contrar = user.getContrasenia();
            String contraEncrip = passwordEncoder.encode(contrar);
            boolean respuestaCambio = userService.cambiarContrasena(contraEncrip, userExistente.getCorreo());
            if (respuestaCambio) {
                attributes.addFlashAttribute("msg_success", "Se ha actualizado de manera exitosa");
            } else {
                attributes.addFlashAttribute("msg_error", "Hubo un error al momento de actualizar");
            }
        }
        return "redirect:/solicitante/home";
    }

    @GetMapping("/registroCita")
    public String registroCita(Cita cita, Model model) {
        model.addAttribute("listaServicio", servicioService.listarTodos());
        model.addAttribute("listaHorarios", horarioCitaService.listarTodos());
        model.addAttribute("listaIntervalos", intervaloService.horarioDisponible());
        return "/solicitante/formCita";
    }

    @PostMapping("/guadarCita")
    public String guardarita(Cita cita, RedirectAttributes attributes, Authentication authentication) {

        User userExist = userService.buscarCorreo(authentication.getName());
        cita.setUser(userService.mostrar(userExist.getId()));
        cita.setEstatus("agendada");

        boolean res = citaService.crearCita(cita);
        if (res) {
            attributes.addFlashAttribute("msg_success", "Se ha registrado de manera exitosa");
        } else {
            attributes.addFlashAttribute("msg_error", "Hubo un error al momento de registrar");
        }
        return "redirect:/solicitante/listCitas";
    }
}
