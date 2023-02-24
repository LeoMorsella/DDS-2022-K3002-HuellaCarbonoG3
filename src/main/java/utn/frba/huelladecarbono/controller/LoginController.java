package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.DTO.LoginDTO;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.UsuarioRepository;

@RestController
@RequestMapping("login/")
public class LoginController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("miembro/{usuarioS}/{contrasenia}")
    private Integer loginMiembro(@PathVariable String usuarioS, @PathVariable String contrasenia) {
        if(this.usuarioRepository.existsByUsername(usuarioS)) {
            Usuario usuario = this.usuarioRepository.findByUsername(usuarioS);

            if(usuario.getRol() == 1 && usuario.getPassword().equals(contrasenia)) {
                return usuario.getIdRol();
            }
        }

            return -1;
    }

    @GetMapping("organizacion/{usuarioS}/{contrasenia}")
    private Integer loginOrganizacion(@PathVariable String usuarioS, @PathVariable String contrasenia) {

        if( this.usuarioRepository.existsByUsername(usuarioS)) {
            Usuario usuario = this.usuarioRepository.findByUsername(usuarioS);

            if(usuario.getRol() == 2 && usuario.getPassword().equals(contrasenia)) {
                return usuario.getIdRol();
            }
        }
        return -1;
    }

    @GetMapping("agenteSectorial/{usuarioS}/{contrasenia}")
    private Integer loginAS(@PathVariable String usuarioS, @PathVariable String contrasenia) {
        if( this.usuarioRepository.existsByUsername(usuarioS)) {
            Usuario usuario = this.usuarioRepository.findByUsername(usuarioS);

            if(usuario.getRol() == 3 && usuario.getPassword().equals(contrasenia)) {
                return usuario.getIdRol();
            }
        }
        return -1;
    }

}
