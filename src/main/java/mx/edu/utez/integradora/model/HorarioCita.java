package mx.edu.utez.integradora.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "horarioVentanilla")
public class HorarioCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHorarioVentanilla;
    //Usar solo cuando sea java.util.Date
    @Temporal(TemporalType.DATE)
	//Se usa para fechas ejemplo (2001-01-31)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha;
    //Se usa solo para horas ya con esta configuracion puede usar solo el Date y así te vitas cambias entre Date y Time
    @Temporal(TemporalType.TIME)
    //Se usa para horas (20:00:00)
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)

    private Date horaInicio;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date horaFin;
	/*
	* Si deseas usar el sql.Date y el sql.Time descomenta esto
	*
	* La M se usa para mes
	*	@DateTimeFormat(pattern="yyyy-MM-dd")
    *	private Date fecha;
    *	La m se usa para minutos
	*	@DateTimeFormat(pattern="hh:mm:ss")
    *	private Date horaInicio;
	*
	*	@DateTimeFormat(pattern="yyyy-MM-dd")
	*	private Date horaInicio;
	*
	* 	NO OLVIDES CAMBIAR
	* 	-los get and set,
	* 	-El HorarioCitaRepository
	* 	-El HorarioCitaService y el serviceImpl
	* 	-El VentanillaController
	*  */
    private int numVentanilla;
    //Estableces la relación para así asociar el registro a un usuario
	@ManyToOne
	@JoinColumn(name = "usuario")
	private User user;

    public HorarioCita(){

    }

	@Override
	public String toString() {
		return "HorarioCita [idHorarioVentanilla=" + idHorarioVentanilla + ", fecha=" + fecha + ", horaInicio="
				+ horaInicio + ", horaFin=" + horaFin + ", numVentanilla="
				+ numVentanilla + "]";
	}

	public HorarioCita(long idHorarioVentanilla, Date fecha, Date horaInicio, Date horaFin,int numVentanilla) {
		this.idHorarioVentanilla = idHorarioVentanilla;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.numVentanilla = numVentanilla;
	}

	public long getIdHorarioVentanilla() {
		return idHorarioVentanilla;
	}

	public void setIdHorarioVentanilla(long idHorarioVentanilla) {
		this.idHorarioVentanilla = idHorarioVentanilla;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public int getNumVentanilla() {
		return numVentanilla;
	}

	public void setNumVentanilla(int numVentanilla) {
		this.numVentanilla = numVentanilla;
	}

   
}
