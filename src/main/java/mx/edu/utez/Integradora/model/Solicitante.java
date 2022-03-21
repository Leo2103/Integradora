package mx.edu.utez.Integradora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "solicitante")
public class Solicitante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Campo obligatorio")
	@Pattern(regexp="[a-zA-Z ]{2,254}")
	@Size(min=2, max=20)
	@Column(nullable = false, length = 20)
	private String nombre;
	
	@NotBlank(message= "Campo obligatorio")
	@Pattern(regexp="[a-zA-Z ]{2,254}")
	@Size(min=2, max=20)
	@Column(nullable = false, length = 40)
	private String apellidos;
	
	@NotBlank(message= "Campo obligatorio")
	@Pattern(regexp="[a-zA-Z ]{2,254}")
	@Size(min=2, max=30)
	@Column(nullable = false, length = 30)
	private String matricula;
	
	@NotBlank(message= "Campo obligatorio")
	@Pattern(regexp="[a-zA-Z ]{2,254}")
	@Size(min=2, max=15)
	@Column(nullable = false, length = 15)
	private String carrera;
	
	@NotBlank(message= "Campo obligatorio")
	@Pattern(regexp="[a-zA-Z ]{2,254}")
	@Size(min=2, max=50)
	@Column(nullable = false, length = 40)
	@Email
	private String correo;
	
	@NotNull(message= "Campo obligatorio")
	@Column(nullable = false, length = 10)
	private Integer numero;

	
	
	public Solicitante(Long id, @NotBlank @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 20) String nombre,
			@NotBlank @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 20) String apellidos,
			@NotBlank @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 30) String matricula,
			@NotBlank @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 15) String carrera,
			@NotBlank @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 50) @Email String correo,
			@NotNull(message = "Campo obligatorio") Integer numero) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.matricula = matricula;
		this.carrera = carrera;
		this.correo = correo;
		this.numero = numero;
	}

	public Solicitante() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
	
 

}
