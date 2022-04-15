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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
        String username = auth.getName();
        User usuario = userService.buscarCorreo(username);
        Long idSesion = usuario.getId();
        model.addAttribute("listHorarios", horarioCitaService.listarTodos());
        return "ventanilla/listHorarios";
    }

    @GetMapping(path = "/formHorario")
    public String gestionHorario(HorarioCita horarioCita, Model model) {
        return "ventanilla/formHorario";
    }

    @PostMapping(path = "/guardarHorario")
    public String guardarHorario(
            @RequestParam(name = "numRepeticiones") int numRepeticiones,
            @RequestParam(name = "fecha", defaultValue = "#{ new java.util.Date() }") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date fecha,
            @RequestParam(name = "horaInicio", defaultValue = "#{ new java.util.Date() }") @DateTimeFormat(pattern = "HH:mm") String horaInicio,
            @RequestParam(name = "horaFin", defaultValue = "#{ new java.util.Date() }") @DateTimeFormat(pattern = "HH:mm") String horaFin,
            @RequestParam(name = "numVentanilla") int numV,
            Authentication auth, HorarioCita horarioCita) throws ParseException {
        String username = auth.getName();
        User userExist = userService.buscarCorreo(username);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date result;
        try {
            result = df.parse(horaInicio);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }



        /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIn = formatFecha.format(fecha);
        Date fechaR = Date.valueOf(fechaIn);

        Date horaF = Date.valueOf(hF);
        horarioCita.setFecha(String.valueOf(fechaR));
        horarioCita.setHoraInicio(String.valueOf(horaI));
        horarioCita.setHoraFin(String.valueOf(horaF));
        String fechaR= String.valueOf(fecha);
        String horaI= String.valueOf(horaInicio);
        String horaF= String.valueOf(horaFin);
       */
        boolean res= horarioCitaService.guardar(horarioCita);
        //boolean res = horarioCitaService.guardarHorario(fecha, horaInicio, horaFin, numV, numRepeticiones, usuario);
        if (res) {
            System.out.println(fecha + "\n" + horaInicio + "\n" + horaFin);
            return "ventanilla/listHorarios";
        } else {
            System.out.println(fecha + "\n" + horaInicio + "\n" + horaFin);
            return "ventanilla/formHorario";
        }

    }
}
