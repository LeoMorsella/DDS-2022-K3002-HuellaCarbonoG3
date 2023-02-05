package utn.frba.huelladecarbono.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utn.frba.huelladecarbono.DTO.LoginDTO;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.UsuarioRepository;

import java.util.logging.Logger;

@RestController("login/")
public class LoginController {
    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("miembro")
    private Integer loginMiembro(@RequestBody LoginDTO login) {
        System.out.println("Comienza la request");
        if( this.usuarioRepository.existsById(Integer.parseInt(login.getUsuario()))) {
            Usuario usuario = this.usuarioRepository.findByUsername(login.getUsuario());

            if(usuario.getRol() == 1 && usuario.getPassword() == login.getContrasenia()) {
                System.out.println("retorna el id");
                return usuario.getIdRol();
            }
        }
        System.out.println("retorna -1");
        return -1;
    }

    @PostMapping("organizacion")
    private Integer loginOrganizacion(@RequestBody LoginDTO login) {
        if( this.usuarioRepository.existsById(Integer.parseInt(login.getUsuario()))) {
            Usuario usuario = this.usuarioRepository.findByUsername(login.getUsuario());

            if(usuario.getRol() == 2 && usuario.getPassword() == login.getContrasenia()) {
                return usuario.getIdRol();
            }
        }
        return -1;
    }

    @PostMapping("agenteSectorial")
    private Integer loginAS(@RequestBody LoginDTO login) {
        if( this.usuarioRepository.existsById(Integer.parseInt(login.getUsuario()))) {
            Usuario usuario = this.usuarioRepository.findByUsername(login.getUsuario());

            if (usuario.getRol() == 3 && usuario.getPassword() == login.getContrasenia()) {
                return usuario.getIdRol();
            }
        }
        return -1;
    }
}
