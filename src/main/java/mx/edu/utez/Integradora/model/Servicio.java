package mx.edu.utez.Integradora.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private String documentosRequeridos;
    private double costo;
    public Servicio(){}
    public Servicio(String nombre, String descripcion, String documentosRequeridos, double costo) {
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
