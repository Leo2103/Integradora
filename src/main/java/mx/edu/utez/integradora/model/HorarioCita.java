package mx.edu.utez.integradora.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "horarioVentanilla")
public class HorarioCita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHorarioVentanilla;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private int numVentanilla;
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

	public HorarioCita(long idHorarioVentanilla, String fecha, String horaInicio, String horaFin,int numVentanilla) {
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public int getNumVentanilla() {
		return numVentanilla;
	}

	public void setNumVentanilla(int numVentanilla) {
		this.numVentanilla = numVentanilla;
	}

   
}
