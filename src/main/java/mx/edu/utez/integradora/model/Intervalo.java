package mx.edu.utez.integradora.model;



import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity

@Table(name = "intervalo")
public class Intervalo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Time hora;
    private boolean enable;
    @ManyToOne
    @JoinColumn(name = "horario_cita")
    private HorarioCita horarioCita;

    public Intervalo() {
    }

    public Intervalo(Time hora, boolean enable, HorarioCita horarioCita) {
        this.hora = hora;
        this.enable = enable;
        this.horarioCita = horarioCita;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public HorarioCita getHorarioCita() {
        return horarioCita;
    }

    public void setHorarioCita(HorarioCita horarioCita) {
        this.horarioCita = horarioCita;
    }
}
