package mx.edu.utez.integradora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cita")
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcita;
    private Time hora;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idHorarioVentanilla")
    private HorarioCita ventanilla;
    @ManyToOne
    @JoinColumn(name = "idServicio")
    private Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "idAtendio")
    private User user;
    private String documentoAnexos;
    private String estatus;
    @ManyToOne
    @JoinColumn(name = "idSolicitante")
    private Solicitante solicitante;
}
