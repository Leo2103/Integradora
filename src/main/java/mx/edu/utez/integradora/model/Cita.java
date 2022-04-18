package mx.edu.utez.integradora.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity

@Table(name = "cita")
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcita;
    private String hora;
    private String fecha;
    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicio servicio;
    private String documentoAnexos;
    private String estatus;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private User user;

    public Cita() {
    }

    public Cita(String hora, String fecha, Servicio servicio, String documentoAnexos, String estatus, User user) {
        this.hora = hora;
        this.fecha = fecha;
        this.servicio = servicio;
        this.documentoAnexos = documentoAnexos;
        this.estatus = estatus;
        this.user = user;
    }

    public long getIdcita() {
        return idcita;
    }

    public void setIdcita(long idcita) {
        this.idcita = idcita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getDocumentoAnexos() {
        return documentoAnexos;
    }

    public void setDocumentoAnexos(String documentoAnexos) {
        this.documentoAnexos = documentoAnexos;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
