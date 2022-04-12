package mx.edu.utez.integradora.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcita;
    private Time hora;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idHorarioVentanilla")
    private HorarioCita ventanilla;
    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "idAtendio")
    private User user;
    private String documentoAnexos;
    private String estatus;
    @ManyToOne
    @JoinColumn(name = "idSolicitante")
    private Solicitante solicitante;
    public Cita(){}
	public Cita(long idcita, Time hora, Date fecha, HorarioCita ventanilla, Servicio servicio, User user,
			String documentoAnexos, String estatus, Solicitante solicitante) {
		super();
		this.idcita = idcita;
		this.hora = hora;
		this.fecha = fecha;
		this.ventanilla = ventanilla;
		this.servicio = servicio;
		this.user = user;
		this.documentoAnexos = documentoAnexos;
		this.estatus = estatus;
		this.solicitante = solicitante;
	}
	public long getIdcita() {
		return idcita;
	}
	public void setIdcita(long idcita) {
		this.idcita = idcita;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public HorarioCita getVentanilla() {
		return ventanilla;
	}
	public void setVentanilla(HorarioCita ventanilla) {
		this.ventanilla = ventanilla;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDocumentoAnexos() {
		return documentoAnexos;
	}
	public void setDocumentoAnexos(String documentoAnexos) {
		this.documentoAnexos = documentoAnexos;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Solicitante getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
	
	
    
}
