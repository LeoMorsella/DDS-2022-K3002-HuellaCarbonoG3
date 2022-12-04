package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.AreaRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.service.IAreaService;
import utn.frba.huelladecarbono.service.IOrganizacionService;

import java.util.List;

@RestController
public class AreaController {

    @Autowired
    private IAreaService interfazArea;

    @Autowired
    private AreaRepository areaRepository;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping("/areas")
    public List<Area> getAreas(){
        return interfazArea.getAreas();
    }

    //Endpoint para obtener solo a las organizaciones que estan activas en la bd
    @GetMapping("/areas/estado")
    public List<Area> getOrganizacionesActivas() {
        return interfazArea.findAreaByEstadoActivo();
    }

    //Endpoint para dar de baja a una organizacion, la baja solamente es logica por lo tanto solo se cambia el estado
    @DeleteMapping("/area/eliminar/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazArea.deleteArea(id);
        return "La Area fue dada de baja correctamente";
    }

    //Endpoint para crear una nueva organizacion
    @PostMapping("/area/crear")
    public String saveOrganizacion(@RequestBody Area area){
        interfazArea.saveArea(area);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/area/editar/{id}")
    public Area cambiarEstadoOrganizacion(@PathVariable Integer id){
        interfazArea.cambiarEstadoArea(id);
        Area area = interfazArea.findArea(id);
        return area;
    }

    @PutMapping("/area/editar/{id}")
    public Area actualizarOrganizacion(@PathVariable Integer id, @RequestBody Area area) throws Exception {
        return interfazArea.modificarArea(id,area);
    }
}
