package utn.frba.huelladecarbono.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frba.huelladecarbono.DTO.LoginDTO;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.UsuarioRepository;

@RestController("login/")
public class LoginController {
    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("miembro")
    private Integer loginMiembro(LoginDTO login) {
        Usuario usuario = this.usuarioRepository.findByUsername(login.getUsuario());

        if(usuario.getRol() == 1 && usuario.getPassword() == login.getContrasenia()) {
            return usuario.getIdRol();
        }
        return -1;
    }

    @PostMapping("organizacion")
    private Integer loginOrganizacion(LoginDTO login) {
        Usuario usuario = this.usuarioRepository.findByUsername(login.getUsuario());

        if(usuario.getRol() == 2 && usuario.getPassword() == login.getContrasenia()) {
            return usuario.getIdRol();
        }
        return -1;
    }

    @PostMapping("agenteSectorial")
    private Integer loginAS(LoginDTO login) {
        Usuario usuario = this.usuarioRepository.findByUsername(login.getUsuario());

        if(usuario.getRol() == 3 && usuario.getPassword() == login.getContrasenia()) {
            return usuario.getIdRol();
        }
        return -1;
    }
}
