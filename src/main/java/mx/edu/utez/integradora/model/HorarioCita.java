package mx.edu.utez.integradora.model;




import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;


@Entity

@Table(name = "horarioVentanilla")
public class HorarioCita implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHorarioVentanilla;
	@Column(name = "fecha")
	private String fecha;

	@Column(name = "hora_inicio")
	private String horaInicio;

	@Column(name = "hora_fin")
	private String horaFin;

	@Min(value = 1, message = "El numero de ventanilla debe ser mínimo 1")
	@Max(value = 20,message = "El numero de ventanilla debe ser maximo 20")
	@Column(name = "num_ventanilla")
	private int numVentanilla;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private User usuario;

	@OneToMany(mappedBy = "horarioCita")
	private Set<Intervalo> intervalo;

	@Override
	public String toString() {
		return "HorarioCita [idHorarioVentanilla=" + idHorarioVentanilla + ", fecha=" + fecha + ", horaInicio="
				+ horaInicio + ", horaFin=" + horaFin + ", numVentanilla="
				+ numVentanilla + "]";
	}


	public HorarioCita() {
	}

	public HorarioCita(String fecha, String horaInicio, String horaFin, @Min(value = 1, message = "El numero de ventanilla debe ser mínimo 1") @Max(value = 20, message = "El numero de ventanilla debe ser maximo 20") int numVentanilla, User usuario, Set<Intervalo> intervalo) {
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.numVentanilla = numVentanilla;
		this.usuario = usuario;
		this.intervalo = intervalo;
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

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Set<Intervalo> getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Set<Intervalo> intervalo) {
		this.intervalo = intervalo;
	}
}
