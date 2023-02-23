package utn.frba.huelladecarbono.model.Seguridad;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.AgenteSectorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.service.ValidadorContraseniasService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Integer rol;
    @Column
    private Integer idRol;


    public Usuario(String username, String password, Integer rol, Integer idRol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.idRol = idRol;
    }

    public Usuario() {

    }
}
