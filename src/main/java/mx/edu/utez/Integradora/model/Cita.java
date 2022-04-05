package mx.edu.utez.Integradora.model;

import javax.persistence.*;
import java.sql.Time;

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Time hora;
    @ManyToOne
    @JoinColumn(name = "idServicio", nullable = false)
    private Servicio servicio;
    //
    @ManyToOne
    @JoinColumn(name = "idSolicitante", nullable = false)
    private Solicitante solicitante;
    @ManyToOne
    @JoinColumn(name = "idHorarioVentanilla", nullable = false)
    private HorarioCita horarioCita;
    private String documentosAnexos;
    private int pago;

    public Cita(Time hora, Servicio servicio, Solicitante solicitante, HorarioCita horarioCita, String documentosAnexos, int pago) {
        this.hora = hora;
        this.servicio = servicio;
        this.solicitante = solicitante;
        this.horarioCita = horarioCita;
        this.documentosAnexos = documentosAnexos;
        this.pago = pago;
    }

    public Cita() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    /*
        public Servicio getServicio() {
            return servicio;
        }

        public void setServicio(Servicio servicio) {
            this.servicio = servicio;
        }
    */
    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public HorarioCita getHorarioCita() {
        return horarioCita;
    }

    public void setHorarioCita(HorarioCita horarioCita) {
        this.horarioCita = horarioCita;
    }

    public String getDocumentosAnexos() {
        return documentosAnexos;
    }

    public void setDocumentosAnexos(String documentosAnexos) {
        this.documentosAnexos = documentosAnexos;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }
}
