package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.service.IOrganizacionService;

import java.util.List;

@RestController
public class OrganizacionController {

    @Autowired
    private IOrganizacionService interfazOrganizacion;

    //Endpoint para obtener a todos las organizaciones
    @GetMapping("/organizaciones")
    public List<Organizacion> getOrganizaciones(){
        return interfazOrganizacion.getOrganizaciones();
    }

    //Endpoint para dar de baja a una organizacion
    @DeleteMapping("organizacion/eliminar/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazOrganizacion.deleteOrganizacion(id);
        return "La organización fue eliminada correctamente";
    }

    //Endpoint para crear una nueva organizacion
    @PostMapping("/organizacion/crear")
    public String saveOrganizacion(@RequestBody Organizacion organizacion){
        interfazOrganizacion.saveOrganizacion(organizacion);
        return "La organización fue creada correctamente";
    }


}
