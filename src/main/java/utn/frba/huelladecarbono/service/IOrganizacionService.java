package utn.frba.huelladecarbono.service;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.List;

public interface IOrganizacionService {

    //metodo para obtener a todas las organizaciones
    public List<Organizacion> getOrganizaciones();

    //Metodo para dar de alta a una organizacion
    public void saveOrganizacion(Organizacion organizacion);

    //Metodo para eliminar una organizacion
    public void deleteOrganizacion(Integer id);

    //Metodo para encontrar a una organizacion
    public Organizacion findOrganizacion(Integer id);
}
