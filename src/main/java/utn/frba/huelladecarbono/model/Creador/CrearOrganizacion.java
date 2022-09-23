package utn.frba.huelladecarbono.model.Creador;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.ArrayList;

public class CrearOrganizacion {

    public static Organizacion crearOrganizacion(String razonSocial, TipoOrg tipo, Clasificacion clasificacion, ArrayList<Miembro> contactosMail, ArrayList<Miembro> contactosWP, Boolean estaActivo, OrganizacionRepository o) {
        Organizacion organizacion = new Organizacion(razonSocial,tipo,clasificacion,contactosMail,contactosWP,estaActivo);
        o.save(organizacion);
        return organizacion;
    }

}
