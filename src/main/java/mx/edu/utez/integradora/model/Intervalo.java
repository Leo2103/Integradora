package mx.edu.utez.integradora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
