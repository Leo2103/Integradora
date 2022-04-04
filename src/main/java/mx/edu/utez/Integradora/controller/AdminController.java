package mx.edu.utez.Integradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
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
    @GetMapping("/formUsuario")
    public String crearUsuario(){
        return "administrador/formUsuario";
    }
    /*@PostMapping("/guardar")
    public String guadarServicio(Servicio servicio, Model model, RedirectAttributes attributes){
        if(servicio.getId()!=null){
            Servicio servicioExistente= servicioService.buscarServicio(servicio.getId());
        }
        servicioService.guardarServicio(servicio);
        return "redirect:/admin/consultarTodos";
    }*/
    @GetMapping("/panelAdm")
	public String panelAdmin() {
		return "administrador/panelAdministrador";
	}
	@GetMapping("/gestionHora")
	public String gestionHorario() {
		return "administrador/gestionarHorario";
	}
}
