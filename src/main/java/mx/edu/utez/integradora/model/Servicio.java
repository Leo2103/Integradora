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

    @NotNull(message = "El nombre del servicio no puede quedar en blanco")
    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo debe de contener letras")
    @Size(min = 5, max = 100, message = "El nombre no cumple con el número de carácteres necesario")
    private String nombre;

    @NotNull(message = "La descripción del servicio no puede quedar en blanco")
    @Pattern(regexp = "[A-Za-z]+", message = "La descripción solo debe de contener letras")
    @Size(min = 5, max = 255, message = "La descripción no cumple con el número de carácteres necesario")
    private String descripcion;
    @NotNull(message = "Los documentos requeridos del servicio no puede quedar en blanco")
    @Pattern(regexp = "[A-Za-z]+", message = "La descripción solo debe de contener letras")
    @Size(min = 5, max = 255, message = "La descripción no cumple con el número de carácteres necesario")
    private String documentosRequeridos;

    @NotNull(message = "El costo del servicio no puede quedar en blanco")
    @Min(value = 0, message = "El precio no puede ser menor a 0")
    private double costo;


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
