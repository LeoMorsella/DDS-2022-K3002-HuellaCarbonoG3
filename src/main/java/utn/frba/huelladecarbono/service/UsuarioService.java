package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
