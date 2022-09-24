package utn.frba.huelladecarbono.model.Creador;

import org.springframework.beans.factory.annotation.Autowired;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.RecorridoRepository;
import utn.frba.huelladecarbono.repository.UsuarioRepository;

import java.util.Calendar;

public class CrearUsuario {


    public static Usuario crearUsuario(String username, String password, Rol rol,UsuarioRepository u){
        Usuario usuario = new Usuario(username,password,rol);
        u.save(usuario);
       // RepositorioUsuarios.getRepositorio().agregarUsuario(usuario);
        return usuario;
    }



}
