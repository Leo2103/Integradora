package mx.edu.utez.integradora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
