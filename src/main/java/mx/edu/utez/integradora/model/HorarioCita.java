package mx.edu.utez.integradora.model;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "horarioVentanilla")
public class HorarioCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHorarioVentanilla;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private int repeticiones;
    private int numVentanilla;

    public HorarioCita(){

    }

	@Override
	public String toString() {
		return "HorarioCita [idHorarioVentanilla=" + idHorarioVentanilla + ", fecha=" + fecha + ", horaInicio="
				+ horaInicio + ", horaFin=" + horaFin + ", repeticiones=" + repeticiones + ", numVentanilla="
				+ numVentanilla + "]";
	}

	public HorarioCita(long idHorarioVentanilla, String fecha, String horaInicio, String horaFin, int repeticiones,
			int numVentanilla) {
		super();
		this.idHorarioVentanilla = idHorarioVentanilla;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.repeticiones = repeticiones;
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

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public int getNumVentanilla() {
		return numVentanilla;
	}

	public void setNumVentanilla(int numVentanilla) {
		this.numVentanilla = numVentanilla;
	}

   
}
