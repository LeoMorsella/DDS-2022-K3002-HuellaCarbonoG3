package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.UsuarioRepository;


import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> getUsuarios() {
        List <Usuario> listaUsuarios = usuarioRepository.findAll();

        return listaUsuarios;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }
    @Override
    public void cambiarEstadoUsuario(Integer id) {
        Usuario usuario = findUsuario(id);
        usuario.setEstaActivo(false);
        this.saveUsuario(usuario);
    }

    @Override
    public Usuario modificarUsuario(Integer id, Usuario usuario){
        Usuario usuarioActualizado = this.findUsuario(id);
        usuarioActualizado.setEstaActivo(usuario.getEstaActivo());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setRol(usuario.getRol());
        usuarioActualizado.setUsername(usuario.getUsername());
        usuarioActualizado.setMiembro(usuario.getMiembro());
        this.saveUsuario(usuarioActualizado);
        return usuarioActualizado;
    }
}
