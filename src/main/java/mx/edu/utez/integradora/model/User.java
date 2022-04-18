package mx.edu.utez.integradora.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 45)
    @NotBlank
    @Size(min=2, max=50)
    private String nombre;
    @Column(nullable = false, length = 45)
    @NotBlank
    @Size(min=2, max=50)
    private String apellidos;
    @Column(nullable = false)
    private boolean enabled;
    @Column(nullable = false, length = 45, unique = true)
    @NotBlank
    @Size(min=10, max=50)
    @Email
    private String correo;

    @NotBlank
    @Size(min=5, max=100)
    private String contrasenia;
    @NotNull
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rol;

    public void agregarRole(Role roles){
        if (rol==null){
            rol= new HashSet<Role>();
        }
        rol.add(roles);
    }

    public User() {
    }

    public User(@NotBlank @Size(min = 2, max = 50) String nombre, @NotBlank @Size(min = 2, max = 50) String apellidos, boolean enabled, @NotBlank @Size(min = 10, max = 50) @Email String correo, @NotBlank @Size(min = 5, max = 100) String contrasenia, @NotNull Set<Role> rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.enabled = enabled;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<Role> getRol() {
        return rol;
    }

    public void setRol(Set<Role> rol) {
        this.rol = rol;
    }
}
