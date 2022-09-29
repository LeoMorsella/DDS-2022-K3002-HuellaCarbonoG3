package utn.frba.huelladecarbono.model.CreadorDeObjetos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoTransportePublico;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreadorDeObjetos {

    @Autowired
    OrganizacionRepository repoOrganizaciones;

    @Autowired
    MiembroRepository repoMiembros;

    @Autowired
    ParadaRepository repoParadas;

    @Autowired
    RecorridoRepository repoRecorridos;

    @Autowired
    UsuarioRepository repoUsuarios;

    @Autowired
    TransportePublicoRepository repoTransportes;



    public Organizacion crearOrganizacion(String razonSocial, TipoOrg tipo, Clasificacion clasificacion, ArrayList<Miembro> contactosMail, ArrayList<Miembro> contactosWP, Boolean estaActivo) {
        Organizacion organizacion = new Organizacion(razonSocial,tipo,clasificacion,contactosMail,contactosWP,estaActivo);
        repoOrganizaciones.save(organizacion);
        return organizacion;
    }

    public Organizacion crearOrganizacionConHC(String razonSocial, TipoOrg tipo, Ubicacion ubicacion, Clasificacion clasificacion, List<Miembro> contactosMail, List<Miembro> contactosWP, List<Double> hcMensual, Double hcPromedio, List<HuellaCarbono> huellasCarbono, Boolean estaActivo) {
        Organizacion organizacion = new Organizacion( razonSocial,  tipo,  ubicacion, clasificacion, contactosMail, contactosWP, hcMensual,  hcPromedio, huellasCarbono,  estaActivo);
        repoOrganizaciones.save(organizacion);
        return organizacion;
    }

    public  Miembro crearMiembro(int id, String nombre, String apellido, String mail, String telefono, Boolean estaActivo) {
        Miembro miembro = new Miembro(id,nombre,apellido,mail,telefono,estaActivo);
        repoMiembros.save(miembro);
        return miembro;
    }

    public  Parada crearParada(String nombre, Ubicacion ubicacion) {
        Parada parada = new Parada(nombre,ubicacion);
        repoParadas.save(parada);
        return parada;
    }


    public  Recorrido crearRecorrido(Organizacion organizacion, Double peso, LocalDate mesInicio, LocalDate mesFin) {
        Recorrido recorrido = new Recorrido(organizacion,peso,mesInicio,mesFin);
        repoRecorridos.save(recorrido);
        return recorrido;
    }

    public  Usuario crearUsuario(String username, String password, Rol rol){
        Usuario usuario = new Usuario(username,password,rol);
        repoUsuarios.save(usuario);
        return usuario;
    }

    public TransportePublico crearTransportePublico(TipoTransportePublico tipoTransportePublico, String linea, ArrayList<Parada> paradas, String ID){
        TransportePublico transportePublico = new TransportePublico(tipoTransportePublico,linea,paradas,ID);
        repoTransportes.save(transportePublico);
        return transportePublico;
    }

}
