package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ManejoAmbiental.Organizacion;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.List;

@Service
public class OrganizacionService implements IOrganizacionService{

    @Autowired
    private OrganizacionRepository organizacionRepository;


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
        organizacionRepository.deleteById(id);
    }

    @Override
    public Organizacion findOrganizacion(Integer id) {
        Organizacion organizacion = organizacionRepository.findById(id).orElse(null);
        return organizacion;
    }
}
