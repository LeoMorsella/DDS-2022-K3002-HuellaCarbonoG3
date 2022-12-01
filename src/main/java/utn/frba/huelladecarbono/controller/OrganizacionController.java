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
import utn.frba.huelladecarbono.service.AreaService;
import utn.frba.huelladecarbono.service.IAreaService;
import utn.frba.huelladecarbono.service.IOrganizacionService;
import utn.frba.huelladecarbono.service.OrganizacionService;

import java.util.List;

@RestController
public class OrganizacionController {

    @Autowired
    private IOrganizacionService interfazOrganizacion;

    @Autowired
    OrganizacionRepository organizacionRepository;

    @Autowired
    private  AreaService areaService;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping("/organizaciones")
    public List<Organizacion> getOrganizaciones(){
        return interfazOrganizacion.getOrganizaciones();
    }

    //Endpoint para obtener solo a las organizaciones que estan activas en la bd
    @GetMapping("/organizaciones/estado")
    public List<Organizacion> getOrganizacionesActivas() {

            return interfazOrganizacion.findOrganizacionByEstadoActivo();

    }

    //Obtener las areas de una organizacion en particular
    @GetMapping("/organizaciones/areas/{id}")
    public List<Area> getAreas(@PathVariable String id) {
        return areaService.findByOrganizacion(id);
    }

    @GetMapping("/organizaciones/huella/{id}")
    public Double getHuella(@PathVariable String id) {
        return interfazOrganizacion.getHuellaTotal(Integer.parseInt(id));
    }

    //Endpoint para dar de baja a una organizacion, la baja solamente es logica por lo tanto solo se cambia el estado
    @DeleteMapping("organizacion/eliminar/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazOrganizacion.deleteOrganizacion(id);
        return "La organización fue dada de baja correctamente";
    }

    //Endpoint para crear una nueva organizacion
    @PostMapping("/organizacion/crear")
    public String saveOrganizacion(@RequestBody Organizacion organizacion){
        interfazOrganizacion.saveOrganizacion(organizacion);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/organizacion/editar/{id}")
    public Organizacion cambiarEstadoOrganizacion(@PathVariable Integer id){
        interfazOrganizacion.cambiarEstadoOrganizacion(id);
        Organizacion orga = interfazOrganizacion.findOrganizacion(id);
        return orga;
    }

    @PutMapping("/organizacion/editar/{id}")
    public Organizacion actualizarOrganizacion(@PathVariable Integer id, @RequestBody Organizacion organizacion) throws Exception {
       return interfazOrganizacion.modificarOrganizacion(id,organizacion);
    }

    @PutMapping("/{organizacionID}/areas/borrar/{areaID}")
    public void borrarArea(@PathVariable String areaID, @PathVariable String organizacionID){
        RepositorioOrganizaciones.getRepositorio()
                .findOrganizacion(Integer.parseInt(organizacionID))
                .borrarArea(areaID);
    }

}
