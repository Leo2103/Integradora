package mx.edu.utez.Integradora.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "solicitante")
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obligatorio")
    @Pattern(regexp = "[a-zA-Z ]{2,254}")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String nombre;

    @NotBlank(message = "Campo obligatorio")
    @Pattern(regexp = "[a-zA-Z ]{2,254}")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 40)
    private String apellidos;

    @NotBlank(message = "Campo obligatorio")
    @Pattern(regexp = "[a-zA-Z ]{2,254}")
    @Size(min = 2, max = 30)
    @Column(nullable = false, length = 30)
    private String matricula;

    @NotBlank(message = "Campo obligatorio")
    @Pattern(regexp = "[a-zA-Z ]{2,254}")
    @Size(min = 2, max = 15)
    @Column(nullable = false, length = 15)
    private String carrera;

    @NotBlank(message = "Campo obligatorio")
    @Pattern(regexp = "[a-zA-Z ]{2,254}")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 40)
    @Email
    private String correo;

    @OneToMany
    @JoinColumn(name = "idUser", nullable = false)
    private User user;



    public Solicitante(String nombre, String apellidos, String matricula, String carrera,String correo,  User user) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.carrera = carrera;
        this.correo = correo;
        this.user=user;
    }

    public Solicitante() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
