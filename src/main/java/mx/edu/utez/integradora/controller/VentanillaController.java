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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public String guardarCambios(User user, RedirectAttributes attributes) {
        User userExistente = userService.mostrar(user.getId());
        if (userExistente == null) {
            return "redirect:/ventanilla/home";
        } else {
            String contrar = user.getContrasenia();
            String contraEncrip = passwordEncoder.encode(contrar);
            boolean respuestaCambio = userService.cambiarContrasena(contraEncrip, userExistente.getCorreo());
            System.out.println(contrar);
            System.out.println(contraEncrip);
            System.out.println(userExistente.getCorreo());
            System.out.println(respuestaCambio);
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
//			redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
        } catch (Exception e) {
//			redirectAttributes.addFlashAttribute("msg_error","Ocurrió un error al cerrar la sesión, intenta de nuevo.");
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
            @RequestParam("numRepeticiones") int numRepeticiones,
            @RequestParam Date fecha,
            @RequestParam Date horaInicio,
            @RequestParam Date horaFin,
            @RequestParam("numVentanilla") int numV,
            Authentication auth) {
        String username = auth.getName();
        User userExist = userService.buscarCorreo(username);
        int usuario= (int) userExist.getId();
        boolean res = horarioCitaService.guardarHorario(fecha,horaInicio,horaFin,numV,numRepeticiones,usuario);
        if (res){
            return "redirect:/ventanilla/consultarHorarios";
        }else {
            return "redirect:/ventanilla/formHorario";
        }
    }
}
