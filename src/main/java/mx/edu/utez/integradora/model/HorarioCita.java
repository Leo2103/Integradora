package mx.edu.utez.integradora.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "horarioVentanilla")
public class HorarioCita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHorarioVentanilla;
    private java.sql.Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private int repeticiones;
    private int numVentanilla;

    public HorarioCita(){

    }

    public HorarioCita(java.sql.Date fecha, Time horaInicio, Time horaFin, int repeticiones, int numVentanilla) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.repeticiones = repeticiones;
        this.numVentanilla = numVentanilla;
    }

    public long getIdHorarioVentanilla() {
        return idHorarioVentanilla;
    }

    public void setIdHorarioVentanilla(long idHorarioVentanilla) {
        this.idHorarioVentanilla = idHorarioVentanilla;
    }

    public java.sql.Date getFecha() {
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
