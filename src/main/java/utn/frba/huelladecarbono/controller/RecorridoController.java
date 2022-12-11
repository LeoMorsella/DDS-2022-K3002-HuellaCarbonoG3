package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.service.IRecorridoService;

import java.util.List;

@RestController
public class RecorridoController {

    @Autowired
    private IRecorridoService interfazRecorrido;

    @GetMapping("/recorridos")
    public List<Recorrido> getRecorridos(){
        return interfazRecorrido.getRecorridos();
    }

    @GetMapping("recorrido/eliminar/{id}")
    public String deleteRecorrido(@PathVariable Integer id) {
        interfazRecorrido.deleteRecorrido(id);
        return "El recorrido fue eliminado correctamente";
    }

    @PostMapping("/recorrido/crear")
    public String saveRecorrido(@RequestBody Recorrido recorrido){
        interfazRecorrido.saveRecorrido(recorrido);
        return "El recorrido fue creado correctamente";
    }

    @PatchMapping("/recorrido/editar/{id}")
    public Recorrido cambiarEstadoRecorrido(@PathVariable Integer id){
        interfazRecorrido.cambiarEstadoRecorrico(id);
        Recorrido recorrido = interfazRecorrido.findRecorrido(id);
        return recorrido;
    }
    @PutMapping("/recorrido/editar/{id}")
    public Recorrido actualizarRecorrido(@PathVariable Integer id, @RequestBody Recorrido recorrido) throws Exception {
        return interfazRecorrido.modificarRecorrido(id,recorrido);
    }
}
