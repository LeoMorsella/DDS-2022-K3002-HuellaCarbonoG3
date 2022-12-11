package utn.frba.huelladecarbono.controller;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.AreaRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.respuestaEndpoint.ResArea;
import utn.frba.huelladecarbono.service.IAreaService;
import utn.frba.huelladecarbono.service.IOrganizacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AreaController {

    @Autowired
    private IAreaService interfazArea;

    @Autowired
    private AreaRepository areaRepository;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping("/areas")
    public List<ResArea> getAreas(){
        List<Area> areas = interfazArea.getAreas();
        return areas.stream().map(ResArea::new).collect(Collectors.toList());
    }
    @GetMapping("/areas/byOrg/{idOrg}")
    public List<ResArea> getAreasByOrg(@PathVariable Integer idOrg) {
        List<Area> areas = interfazArea.findByOrganizacion(idOrg);
        return areas.stream().map(ResArea::new).collect(Collectors.toList());
    }

    //Endpoint para obtener solo a las organizaciones que están activas en la bd
    @GetMapping("/areas/estado")
    public List<Area> getOrganizacionesActivas() {
        return interfazArea.findAreaByEstadoActivo();
    }

    @GetMapping("/area/eliminar/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazArea.deleteArea(id);
        return "El Area fue dada de baja correctamente";
    }

    @PostMapping("/area/crear")
    public String saveArea(@RequestBody String areaJson) throws ParseException {
        interfazArea.crearArea(areaJson);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/area/editar/{id}")
    public void cambiarEstadoArea(@PathVariable Integer id){
        interfazArea.cambiarEstadoArea(id);
        Area area = interfazArea.findArea(id);
    }

    @PutMapping("/area/editar/{id}")
    public void actualizarOrganizacion(@PathVariable Integer id, @RequestBody Area area) throws Exception {
        interfazArea.modificarArea(id,area);
    }
}
