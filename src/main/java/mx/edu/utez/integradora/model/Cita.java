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
}
