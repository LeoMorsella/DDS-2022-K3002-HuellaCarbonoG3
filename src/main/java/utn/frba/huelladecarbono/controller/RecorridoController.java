package utn.frba.huelladecarbono.controller;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.repository.RecorridoRepository;
import utn.frba.huelladecarbono.respuestaEndpoint.ResRecorrido;
import utn.frba.huelladecarbono.service.IRecorridoService;
import utn.frba.huelladecarbono.service.RecorridoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecorridoController {

    @Autowired
    private IRecorridoService interfazRecorrido;
    @Autowired
    private OrganizacionRepository organizacionRepository;
    @Autowired
    private RecorridoRepository recorridoRepository;
    @Autowired
    private MiembroRepository miembroRepository;

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
    public void createRecorrido(@RequestBody String recorridoJson, @PathVariable Integer orgId) throws ParseException {
       JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(recorridoJson);
        Recorrido recorrido = new Recorrido();
         recorrido.setNombre((String) jObject.get("nombre"));
        recorrido.setOrganizacion(organizacionRepository.getById(orgId));
        recorrido.setCantidadDeTrayectos((Integer) jObject.get("trayectos"));
        recorridoRepository.save(recorrido);
    }

    @PostMapping("recorrido/agregarMiembro/{miembroId}")
    public void agregarRecorridoMiembro(@PathVariable Integer miembroId, @RequestBody String recorridoStr) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(recorridoStr);
        Recorrido recorrido = recorridoRepository.getById((Integer) jObject.get("idRecorrido"));
        recorrido.setPeso((Double) jObject.get("peso"));
        recorrido.setFechaInicio((String) jObject.get("fechaInicio"));
        recorrido.setFechaFin((String) jObject.get("fechaFin"));
        recorridoRepository.save(recorrido);

        Miembro miembro = miembroRepository.getById(miembroId);
        miembro.addRecorrido(recorrido);
        miembroRepository.save(miembro);
    }

    @GetMapping("recorrido/miembro/{miembroId}")
    public List<ResRecorrido> getRecorridosMiembro(@PathVariable Integer miembroId) {
        List<Recorrido> recorridos = miembroRepository.getById(miembroId).getRecorridos();
        List<ResRecorrido> res = new ArrayList<>();
        for (Recorrido recorrido : recorridos) {
            res.add(new ResRecorrido(recorrido));
        }
        return res;
    }

    @GetMapping("recorrido/miembro/eliminar/{miembroId}/{recorridoId}")
    public void eliminarRecorridoMiembro(@PathVariable Integer miembroId, @PathVariable Integer recorridoId) {
        Miembro miembro = miembroRepository.getById(miembroId);
        miembro.eliminarRecorrido(recorridoRepository.getById(recorridoId));
        miembroRepository.save(miembro);
    }
}

























