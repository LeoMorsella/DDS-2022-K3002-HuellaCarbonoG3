package utn.frba.huelladecarbono.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.service.IUbicacionService;
import utn.frba.huelladecarbono.service.UbicacionService;

@RestController
public class UbicacionController {

    @Autowired
    private IUbicacionService interfazUbicacion;

    @GetMapping("/ubicaciones")
    public List<Ubicacion> getUbicacion() {return interfazUbicacion.getUbicacion();}

    @DeleteMapping("ubicacion/eliminar/{id}")
    public String deleteUbicacion(@PathVariable Integer id) {
        interfazUbicacion.deleteUbicacion(id);
        return "La ubicacion ha sido eliminada";
    }

    @PostMapping("/ubicacion/crear")
    public String saveUbicacion(@RequestBody Ubicacion ubicacion) {
        interfazUbicacion.saveUbicacion(ubicacion);
        return "La ubicacion fue creada correctamente";
    }
}
