package mx.edu.utez.integradora.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "solicitante")
public class Solicitante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String matricula;
    private String carrera;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User usuario;

    public Solicitante() {
    }

    public Solicitante(String matricula, String carrera, String telefono, User usuario) {
        this.matricula = matricula;
        this.carrera = carrera;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
