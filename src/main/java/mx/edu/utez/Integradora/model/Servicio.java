package mx.edu.utez.Integradora.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Servicio {

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
	@Size(min=2, max=100)
	@Column(nullable = false, length = 100)
	private String descripcion;
	
	@Pattern(regexp="[a-zA-Z ]{2,254}")
	@Size(min=2, max=15)
	@Column(nullable = false, length = 15)
	private String documento;
	
	@NotNull(message= "Campo obligatorio")
	@Column(nullable = false, length = 10)
	private double costo;
	
	

	public Servicio(Long id,
			@NotBlank(message = "Campo obligatorio") @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 20) String nombre,
			@NotBlank(message = "Campo obligatorio") @Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 100) String descripcion,
			@Pattern(regexp = "[a-zA-Z ]{2,254}") @Size(min = 2, max = 15) String documento,
			@NotNull(message = "Campo obligatorio") double costo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.documento = documento;
		this.costo = costo;
	}

	public Servicio() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	
	
	
	
}
