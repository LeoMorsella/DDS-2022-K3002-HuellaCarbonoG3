package utn.frba.huelladecarbono.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Provincia;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.service.AreaService;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCMiembro;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCOrganizacion;
import utn.frba.huelladecarbono.repository.SectorTerritorialRepository;
import utn.frba.huelladecarbono.service.*;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCOrganizacion;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.HCInforme;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private SectorTerritorialController stc;

    @Autowired
    private UbicacionController uc;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping({"/", ""})
    public List<Organizacion> getOrganizaciones(){
       //obtener organizaciones, modificar las areas y setear la organizacion en null
        List<Organizacion> organizaciones = interfazOrganizacion.getOrganizaciones();
        for (Organizacion organizacion : organizaciones) {
            organizacion.ponerOrgDentroDeAreasEnNull();
        }
        return organizaciones;
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
    @GetMapping("{organizacionId}/miembrosEnEspera")
    public HashMap<Miembro, Area> miembrosEnEspera(@PathVariable Integer organizacionId) {
        return organizacionRepository.getById(organizacionId).getMiembrosEnEspera();
    }
    @PatchMapping("{organizacionId}/aceptarMiembro")
    public void aceptarMiembro(@PathVariable Integer organizacionId, @RequestParam Integer areaId, @RequestParam Integer miembroId) throws Exception {
        Miembro miembro = RepositorioMiembros.getRepositorio().findMiembro(miembroId);
        organizacionRepository.getById(organizacionId).getArea(areaId).aceptarMiembro(miembro);
    }

    @PatchMapping("{organizacionId}/rechazarMiembro")
    public void rechazarMiembro(@PathVariable Integer organizacionId, @RequestParam Integer areaId, @RequestParam Integer miembroId) throws Exception {
        Miembro miembro = RepositorioMiembros.getRepositorio().findMiembro(miembroId);
        organizacionRepository.getById(organizacionId).getArea(areaId).rechazarMiembro(miembro);
    }

    @GetMapping("calcularHuella/{org}/{diaI}/{mesI}/{anioI}/{diaF}/{mesF}/{anioF}/")
    public Double calcularHuella(@PathVariable Integer org, @PathVariable Integer diaI, @PathVariable Integer mesI, @PathVariable Integer anioI, @PathVariable Integer diaF, @PathVariable Integer mesF, @PathVariable Integer anioF) {
        LocalDate fechaI = LocalDate.of(anioI, mesI, diaI);
        LocalDate fechaF = LocalDate.of(anioF, mesF, diaF);
        Organizacion organizacion = interfazOrganizacion.findOrganizacion(org);
        return CalculadoraHCOrganizacion.calcularHC(organizacion, fechaI, fechaF);
    }

    //PARA HANDLEBAR - REPORTES
    @GetMapping("/HCSectores")
    public List<HCInforme> HCSectores() {
        List<HCInforme> res = new ArrayList<>();
        List<SectorTerritorial> sectores = stc.getSectorTerritorial();
        for (SectorTerritorial sector : sectores) {
            Double hc = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            res.add(new HCInforme(sector.getId().toString(), hc));
        }
        return res;
    }

    @GetMapping("/HCTipoOrg")
    public List<HCInforme> HCTipoOrg() {
        List<TipoOrg> tipos = Arrays.stream(TipoOrg.values()).toList();
        List<HCInforme> res = new ArrayList<>();
        for (TipoOrg tipo : tipos) {
            List<Organizacion> organizaciones = interfazOrganizacion.getOrganizaciones().stream().filter(org -> org.getTipo().equals(tipo)).toList();
            Double hc = 0.0;
            for (Organizacion org : organizaciones) {
                hc += CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(org,LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            }
            res.add(new HCInforme(tipo.toString(),hc));
        }
        return res;
    }

    @GetMapping("/HCProvincia")
    public List<HCInforme> HCProvincia() throws IOException {
        List<Provincia> provincias = Arrays.stream(uc.getProvincias(9)).toList();
        List<HCInforme> res = new ArrayList<>();

        for (Provincia provincia : provincias) {
            Double hc = 0.0;
            List<Organizacion> organizaciones = interfazOrganizacion.getOrganizaciones().stream().filter(org -> org.getUbicacion().getProvincia().equals(provincia)).toList();
            for (Organizacion org : organizaciones) {
                hc += CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(org,LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            }
            System.out.println(provincia.getNombre());
            System.out.println(hc);
            res.add(new HCInforme(provincia.getNombre(), hc));
        }
        return res;
    }

    public List<HCInforme> HCPropia(Integer orgId) {
        List<HCInforme> res = new ArrayList<>();
        List<Area> areas = areaService.findByOrganizacion(String.valueOf(orgId));
        for (Area area : areas) {
            Double hc = CalculadoraHCService.getCalculadoraHC().calcularHCArea(area,LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            res.add(new HCInforme(area.getNombre(), hc));
        }
        return res;
    }
}
