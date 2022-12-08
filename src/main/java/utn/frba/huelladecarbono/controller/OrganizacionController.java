package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.service.AreaService;
import utn.frba.huelladecarbono.service.IAreaService;
import utn.frba.huelladecarbono.service.IOrganizacionService;
import utn.frba.huelladecarbono.service.OrganizacionService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("organizaciones/")
public class OrganizacionController {

    @Autowired
    private IOrganizacionService interfazOrganizacion;

    @Autowired
    OrganizacionRepository organizacionRepository;

    @Autowired
    OrganizacionService organizacionService;

    @Autowired
    private  AreaService areaService;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping({"/", ""})
    public List<Organizacion> getOrganizaciones(){
        return interfazOrganizacion.getOrganizaciones();
    }

    @GetMapping("/{id}")
    public Organizacion getOrganizacionPorID(@PathVariable String id) throws Exception {
        return interfazOrganizacion.findOrganizacion(Integer.parseInt(id));
    }

    //Endpoint para obtener solo a las organizaciones que estan activas en la bd
    @GetMapping("/estado")
    public List<Organizacion> getOrganizacionesActivas() {

            return interfazOrganizacion.findOrganizacionByEstadoActivo();

    }

    //Obtener las areas de una organizacion en particular
    @GetMapping("/areas/{id}")
    public List<Area> getAreas(@PathVariable String id) {
        return areaService.findByOrganizacion(id);
    }

    @GetMapping("/huella/{id}")
    public Double getHuella(@PathVariable String id) {
        return interfazOrganizacion.getHuellaTotal(Integer.parseInt(id));
    }

    @GetMapping("/{id}/contactosWp")
    public List<Miembro> getContactosWp(@PathVariable String id){
        int idn = Integer.parseInt(id);
        return organizacionService.findOrganizacion(idn).getContactosWP();
    }

    @GetMapping("/{id}/contactosMail")
    public List<Miembro> getContactosMail(@PathVariable String id){
        int idn = Integer.parseInt(id);
        return organizacionService.findOrganizacion(idn).getContactosMail();
    }

    //Endpoint para dar de baja a una organizacion, la baja solamente es logica por lo tanto solo se cambia el estado
    @DeleteMapping("/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazOrganizacion.deleteOrganizacion(id);
        return "La organización fue dada de baja correctamente";
    }

    @PostMapping("/crear")
    public String saveOrganizacion(@RequestBody Organizacion organizacion){
        interfazOrganizacion.saveOrganizacion(organizacion);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/editarEstado/{id}")
    public Organizacion cambiarEstadoOrganizacion(@PathVariable Integer id){
        interfazOrganizacion.cambiarEstadoOrganizacion(id);
        Organizacion orga = interfazOrganizacion.findOrganizacion(id);
        return orga;
    }

    @PutMapping("/editar/{id}")
    public Organizacion actualizarOrganizacion(@PathVariable Integer id, @RequestParam String razonSocial, @RequestParam String tipo, @RequestParam String clasificacion,  @RequestParam Boolean estaActivo) throws Exception {
        Organizacion org = new Organizacion(razonSocial, TipoOrg.valueOf(tipo), null, Clasificacion.valueOf(clasificacion), null, null, null, null, null, null, estaActivo);
        return interfazOrganizacion.modificarOrganizacion(id,org);
    }

    @PutMapping("/{organizacionID}/areas/borrar/{areaID}")
    public void borrarArea(@PathVariable String areaID, @PathVariable String organizacionID){
        RepositorioOrganizaciones.getRepositorio()
                .findOrganizacion(Integer.parseInt(organizacionID))
                .borrarArea(areaID);
    }
    @GetMapping("{organizacionId}/miembros")
    public List<Miembro> miembros(@PathVariable Integer organizacionId) {
        return organizacionRepository.getById(organizacionId).getMiembros();
    }
    @GetMapping("{organizacionId}/miembros")
    public HashMap<Miembro, Area> miembrosEnEspera(@PathVariable Integer organizacionId) {
        return organizacionRepository.getById(organizacionId).getMiembrosEnEspera();
    }

    @PatchMapping("{organizacionId}/aceptarMiembro/")
    public void aceptarMiembro(@PathVariable Integer organizacionId, @RequestParam Integer areaId, @RequestParam Integer miembroId) throws Exception {
        Miembro miembro = RepositorioMiembros.getRepositorio().findMiembro(miembroId);
        organizacionRepository.getById(organizacionId).getArea(areaId).aceptarMiembro(miembro);
    }

    @PatchMapping("{organizacionId}/rechazarMiembro/")
    public void rechazarMiembro(@PathVariable Integer organizacionId, @RequestParam Integer areaId, @RequestParam Integer miembroId) throws Exception {
        Miembro miembro = RepositorioMiembros.getRepositorio().findMiembro(miembroId);
        organizacionRepository.getById(organizacionId).getArea(areaId).rechazarMiembro(miembro);
    }
}
