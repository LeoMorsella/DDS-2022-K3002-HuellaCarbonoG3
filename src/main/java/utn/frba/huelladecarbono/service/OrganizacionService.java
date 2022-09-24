package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.List;

@Service
public class OrganizacionService implements IOrganizacionService{

    @Autowired
    private OrganizacionRepository organizacionRepository;


    @Override
    public Organizacion findById(Integer id) throws Exception {
        return organizacionRepository.findById(id).get();
    }

    @Override
    public List<Organizacion> getOrganizaciones() {
        List <Organizacion> listaOrganizaciones = organizacionRepository.findAll();

        return listaOrganizaciones;
    }

    @Override
    public void saveOrganizacion(Organizacion organizacion) {
        organizacionRepository.save(organizacion);
    }

    @Override
    public void deleteOrganizacion(Integer id) {
        cambiarEstadoOrganizacion(id);
    }


    @Override
    public Organizacion findOrganizacion(Integer id) {
        Organizacion organizacion = organizacionRepository.findById(id).orElse(null);
        return organizacion;
    }


    @Override
    public List<Organizacion> findOrganizacionByEstadoActivo() {

       return organizacionRepository.findByEstaActivo(true);
    }


    @Override
    public void cambiarEstadoOrganizacion(Integer id) {
        Organizacion organizacion = findOrganizacion(id);
        organizacion.setEstaActivo(false);

        this.saveOrganizacion(organizacion);
    }


}
