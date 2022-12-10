package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCMiembro;
import utn.frba.huelladecarbono.service.IMiembroService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/miembro")
public class MiembroController {

    @Autowired
    private IMiembroService interfazMiembro;

    MiembroRepository miembroRepository;

    @GetMapping("/{id}")
    public Miembro getMiembroPorID(@PathVariable String id) throws Exception {
        return interfazMiembro.findMiembro(Integer.parseInt(id));
    }
    @GetMapping("/")
    public List<Miembro> getMiembros(){
        return interfazMiembro.getMiembros();
    }

    //Endpoint para dar de baja a un miembro
    @DeleteMapping("/eliminar/{id}")
    public String deleteMiembro(@PathVariable Integer id) {
        interfazMiembro.cambiarEstadoMiembro(id);
        return "El miembro fue eliminado correctamente";
    }

    //Endpoint para crear a un nuevo miembro
    @PostMapping("/crear")
    public String saveMiembro(@RequestBody Miembro miembro){
        interfazMiembro.saveMiembro(miembro);
        return "El miembro fue creado correctamente";
    }

    @PatchMapping("/editarEstado/{id}")
    public Miembro cambiarEstadoMiembro(@PathVariable Integer id){
        interfazMiembro.cambiarEstadoMiembro(id);
        Miembro miembro = interfazMiembro.findMiembro(id);
        return miembro;
    }
    //Endpoint para modificar a un usuario
    @PutMapping("/editar/{id}")
    public Miembro actualizarMiembro(@PathVariable Integer id, @RequestBody String miembro) throws Exception {
        return interfazMiembro.modificarMiembro(id,miembro);
    }

    @PostMapping("/calcularHuella/{miembroId}")
    public HashMap<Double, Double> calcularHuella(@PathVariable Integer miembroId, @RequestBody LocalDate fechaInicio, @RequestBody LocalDate fechaFin, @RequestBody Integer orgId) throws Exception {
        Double huella = CalculadoraHCMiembro.calcularHC(interfazMiembro.findMiembro(miembroId), fechaInicio, fechaFin, RepositorioOrganizaciones.getRepositorio().findOrganizacion(orgId));
        Double impacto = CalculadoraHCMiembro.calcularImpactoIndividual(interfazMiembro.findMiembro(miembroId),RepositorioOrganizaciones.getRepositorio().findOrganizacion(orgId), fechaInicio, fechaFin );
        HashMap<Double, Double> resultado = new HashMap<>();
        resultado.put(huella, impacto);
        return resultado;
        //Verificar si usar handlebars o no, ya que es carga a BD y visualizacion
    }

}
