package utn.frba.huelladecarbono.model.Creador;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.RecorridoRepository;
import utn.frba.huelladecarbono.repository.UsuarioRepository;

import java.util.Calendar;

public class CreaElementos {

    public Usuario crearUsuario(String username, String password, Rol rol,UsuarioRepository u){
        Usuario usuario = new Usuario(username,password,rol);
        u.save(usuario);
       // RepositorioUsuarios.getRepositorio().agregarUsuario(usuario);
        return usuario;
    }

    public Recorrido crearRecorrido(Organizacion organizacion, Double peso, Calendar mesInicio, Calendar mesFin, RecorridoRepository r) {
        Recorrido recorrido = new Recorrido(organizacion,peso,mesInicio,mesFin);
        r.save(recorrido);
       // RepositorioRecorrido.getRepositorio().agregarRecorrido(recorrido);
        return recorrido;
    }

}
