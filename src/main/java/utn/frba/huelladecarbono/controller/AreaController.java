package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.service.IOrganizacionService;

import java.util.List;

@RestController
public class AreaController {

    @Autowired
    private IAreaService interfazArea;

    @Autowired
    AreaRepository AreaRepository;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping("/areas")
    public List<Area> getAreas(){
        return interfazArea.getAreas();
    }

    //Endpoint para obtener solo a las organizaciones que estan activas en la bd
    @GetMapping("/areas/estado")
    public List<Organizacion> getOrganizacionesActivas() {

        return interfazArea.findAreaByEstadoActivo();

    }

    //Endpoint para dar de baja a una organizacion, la baja solamente es logica por lo tanto solo se cambia el estado
    @DeleteMapping("area/eliminar/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazArea.deleteArea(id);
        return "La Area fue dada de baja correctamente";
    }

    //Endpoint para crear una nueva organizacion
    @PostMapping("/area/crear")
    public String saveOrganizacion(@RequestBody Area area){
        interfazArea.saveArea(organizacion);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/organizacion/editar/{id}")
    public Organizacion cambiarEstadoOrganizacion(@PathVariable Integer id){
        interfazArea.cambiarEstadoArea(id);
        Area area = interfazArea.findArea(id);
        return area;
    }

    @PutMapping("/area/editar/{id}")
    public Organizacion actualizarOrganizacion(@PathVariable Integer id, @RequestBody Organizacion organizacion) throws Exception {
        return interfazArea.modificarArea(id,organizacion);
    }
}
