package mx.edu.utez.Integradora.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Time;
import java.util.Date;

@Table(name="horarioVentanilla")
public class HorarioCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Future
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    @Min(value = 0)
    private int repeticiones;
    @Min(value = 0)
    @Max(value = 10)
    private int numVentanilla;

    public HorarioCita() {
    }

    public HorarioCita(Date fecha, Time horaInicio, Time horaFin, int repeticiones, int numVentanilla) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.repeticiones = repeticiones;
        this.numVentanilla = numVentanilla;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getNumVentanilla() {
        return numVentanilla;
    }

    public void setNumVentanilla(int numVentanilla) {
        this.numVentanilla = numVentanilla;
    }
}
