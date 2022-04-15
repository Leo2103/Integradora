package mx.edu.utez.integradora.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
   
}
