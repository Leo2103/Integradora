package mx.edu.utez.Integradora.model;

import javax.persistence.*;
import java.sql.Time;@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcita;
    private Time hora;
    @ManyToOne
    @JoinColumn(name = "idHorarioVentanilla")
    private HorarioCita ventanilla;
    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicio servicio;
    private String documentoAnexos;
    private int pago;
    @ManyToOne
    @JoinColumn(name = "idSolicitante")
    private Solicitante solicitante;
    public Cita(){}
    public Cita(Time hora, HorarioCita ventanilla, Servicio servicio, String documentoAnexos, int pago, Solicitante solicitante) {
        this.hora = hora;
        this.ventanilla = ventanilla;
        this.servicio = servicio;
        this.documentoAnexos = documentoAnexos;
        this.pago = pago;
        this.solicitante = solicitante;
    }

    public long getIdcita() {
        return idcita;
    }

    public void setIdcita(long idcita) {
        this.idcita = idcita;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public HorarioCita getVentanilla() {
        return ventanilla;
    }

    public void setVentanilla(HorarioCita ventanilla) {
        this.ventanilla = ventanilla;
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

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
}
