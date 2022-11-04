package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.repository.AreaRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.List;

@Service
public class AreaService implements IAreaService{

    @Autowired
    private AreaRepository areaRepository;


    @Override
    public Area findById(Integer id) throws Exception {
        return areaRepository.findById(id).get();
    }

    @Override
    public List<Area> getAreas() {
        List <Area> listaAreas = areaRepository.findAll();
        return listaAreas;
    }

    @Override
    public void saveArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void deleteArea(Integer id) {
        cambiarEstadoArea(id);
    }


    @Override
    public Area findArea(Integer id) {
        Area area = areaRepository.findById(id).orElse(null);
        return area;
    }


    @Override
    public List<Area> findAreaByEstadoActivo() {

        return areaRepository.findByEstaActivo(true);
    }


    @Override
    public void cambiarEstadoArea(Integer id) {
        Area area = findArea(id);
        area.setEstaActivo(false);

        this.saveArea(area);
    }

    @Override
    public Area modificarArea(Integer id, Area area) {
        return null;
    }


    @Override
    public Area crearArea(Area area) {
        areaRepository.save(area);
        return area;
    }

}
