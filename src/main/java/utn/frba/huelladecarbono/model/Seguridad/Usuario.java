package utn.frba.huelladecarbono.model.Seguridad;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioUsuarios;
import utn.frba.huelladecarbono.service.ValidadorContraseniasService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.concurrent.TimeUnit;

@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private int cantIntentos = 0;
    @OneToOne
    private Miembro miembro;

    public static Usuario nuevoUsuario(String username, String password, Rol unrol){
        Usuario nuevoUsuario = new Usuario(username, password, unrol);
        RepositorioUsuarios.getRepositorio().agregarUsuario(nuevoUsuario);
        return nuevoUsuario;
    }
    public Usuario(String username, String password, Rol unRol){
        this.validarCredencialesUser(username, password);
        this.rol = unRol;
    }

    private void validarCredencialesUser(String user, String psw){
        this.username = user;
        this.password = psw;
        ValidadorContraseniasService.getValidadorContrasenias().validarPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCantIntentos() {
        return cantIntentos;
    }

    public Rol getRol() {
        return rol;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setCantIntentos(int cantIntentos) {
        this.cantIntentos = cantIntentos;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public void validarLogueo(String password) throws InterruptedException {
        if(this.rol == Rol.MIEMBRO) {
            if(this.password != password) {
                cantIntentos += 1;
                TimeUnit.SECONDS.sleep(2 ^ cantIntentos);
            }
        }
    }

    public Usuario() {
    }

    public Usuario(String username, String password, Rol rol, int cantIntentos, Miembro miembro) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.cantIntentos = cantIntentos;
        this.miembro = miembro;
    }
}
