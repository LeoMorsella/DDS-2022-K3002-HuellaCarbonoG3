package utn.frba.huelladecarbono.model.CreadorDeObjetos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.ArrayList;
@Component
public class CreadorDeObjetos {

    @Autowired
    OrganizacionRepository repoOrganizaciones;

    @Autowired
    MiembroRepository repoMiembros;


    public Organizacion crearOrganizacion(String razonSocial, TipoOrg tipo, Clasificacion clasificacion, ArrayList<Miembro> contactosMail, ArrayList<Miembro> contactosWP, Boolean estaActivo) {
        Organizacion organizacion = new Organizacion(razonSocial,tipo,clasificacion,contactosMail,contactosWP,estaActivo);
        repoOrganizaciones.save(organizacion);
        return organizacion;
    }

    public  Miembro crearMiembro(int id, String nombre, String apellido, String mail, String telefono) {
        Miembro miembro = new Miembro(id,nombre,apellido,mail,telefono);
        repoMiembros.save(miembro);
        return miembro;
    }



}
