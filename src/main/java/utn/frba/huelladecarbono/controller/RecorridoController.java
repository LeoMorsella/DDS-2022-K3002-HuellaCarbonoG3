package utn.frba.huelladecarbono.controller;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.respuestaEndpoint.ResRecorrido;
import utn.frba.huelladecarbono.service.IRecorridoService;
import utn.frba.huelladecarbono.service.RecorridoService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecorridoController {

    @Autowired
    private IRecorridoService interfazRecorrido;

    @GetMapping("/recorridos/{orgId}")
    public List<ResRecorrido> getRecorridos(@PathVariable Integer orgId) {
        List<Recorrido> recorridos = interfazRecorrido.getRecorridos().stream().filter(recorrido -> recorrido.getOrganizacion().getId() == orgId).toList();
        List<ResRecorrido> res = new ArrayList<>();
        for (Recorrido recorrido : recorridos) {
            res.add(new ResRecorrido(recorrido));
        }
        return res;
    }

    @GetMapping("/recorrido/eliminar/{id}")
    public String deleteRecorrido(@PathVariable Integer id) {
        interfazRecorrido.deleteRecorrido(id);
        return "El recorrido fue eliminado correctamente";
    }

    @PostMapping("/recorrido/agregar/{orgId}")
    public String createRecorrido(@RequestBody String recorridoJson, @PathVariable Integer orgId) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(recorridoJson);
        Recorrido recorrido = new Recorrido();
        recorrido.setNombre((String) jObject.get("nombre"));
    }

}

























