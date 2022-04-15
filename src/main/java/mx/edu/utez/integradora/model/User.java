package mx.edu.utez.integradora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(nullable = false, length = 45)
    private String apellidos;
    @Column(nullable = false, length = 45, unique = true)
    private String correo;
    private String contrasenia;
    private boolean enabled;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rol;

    public void agregarRole(Role roles){
        if (rol==null){
            rol= new HashSet<Role>();
        }
        rol.add(roles);
    }


}
