package mx.edu.utez.integradora.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private String documentosRequeridos;
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
