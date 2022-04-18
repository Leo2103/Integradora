package mx.edu.utez.integradora.model;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "El nombre del servicio no puede quedar en blanco")
    @Size(min = 5, max = 100, message = "El nombre no cumple con el número de carácteres necesario")
    private String nombre;

    @NotBlank(message = "La descripción del servicio no puede quedar en blanco")
    private String descripcion;
    @NotBlank(message = "Los documentos requeridos del servicio no puede quedar en blanco")
    @Size(min = 3, max = 255, message = "La descripción no cumple con el número de carácteres necesario")
    private String documentosRequeridos;

    @NotNull(message = "El costo del servicio no puede quedar en blanco")
    @Min(value = 0, message = "El precio no puede ser menor a 0")
    private double costo;

    public Servicio() {
    }

    public Servicio(@NotBlank(message = "El nombre del servicio no puede quedar en blanco") @Size(min = 5, max = 100, message = "El nombre no cumple con el número de carácteres necesario") String nombre, @NotBlank(message = "La descripción del servicio no puede quedar en blanco") String descripcion, @NotBlank(message = "Los documentos requeridos del servicio no puede quedar en blanco") @Size(min = 3, max = 255, message = "La descripción no cumple con el número de carácteres necesario") String documentosRequeridos, @NotNull(message = "El costo del servicio no puede quedar en blanco") @Min(value = 0, message = "El precio no puede ser menor a 0") double costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.documentosRequeridos = documentosRequeridos;
        this.costo = costo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDocumentosRequeridos() {
        return documentosRequeridos;
    }

    public void setDocumentosRequeridos(String documentosRequeridos) {
        this.documentosRequeridos = documentosRequeridos;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
