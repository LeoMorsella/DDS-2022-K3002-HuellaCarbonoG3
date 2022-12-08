package utn.frba.huelladecarbono.controller;


import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCMiembro;
import utn.frba.huelladecarbono.service.IMiembroService;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
//@RequestMapping(path="/api")
public class MiembroController {

    @Autowired
    private IMiembroService interfazMiembro;

    MiembroRepository miembroRepository;

    //Prueba para obtener a un miembro
    @RequestMapping(value = "miembro")
    public Miembro getMiembroPrueba(){
        Miembro miembro = new Miembro();
        miembro.setId(1);
        miembro.setNombre("Gonzalo");
        miembro.setApellido("Duarte");
        miembro.setMail("gduarte@gmail.com");
        miembro.setTelefono("11256635");
     return miembro;
    }

    @GetMapping("/miembros/{id}")
    public Miembro getMiembroPorID(@PathVariable String id) throws Exception {
        return interfazMiembro.findMiembro(Integer.parseInt(id));
    }

    //Endpoint para obtener a todos los miembros
    @GetMapping("/miembros")
    public List<Miembro> getMiembros(){
        return interfazMiembro.getMiembros();
    }

    //Endpoint para dar de baja a un miembro
    @DeleteMapping("miembro/eliminar/{id}")
    public String deleteMiembro(@PathVariable Integer id) {
        interfazMiembro.cambiarEstadoMiembro(id);
        return "El miembro fue eliminado correctamente";
    }

    //Endpoint para crear a un nuevo miembro
    @PostMapping("/miembro/crear")
    public String saveMiembro(@RequestBody Miembro miembro){
        interfazMiembro.saveMiembro(miembro);
        return "El miembro fue creado correctamente";
    }

    @PatchMapping("/miembro/editar/{id}")
    public Miembro cambiarEstadoMiembro(@PathVariable Integer id){
        interfazMiembro.cambiarEstadoMiembro(id);
        Miembro miembro = interfazMiembro.findMiembro(id);
        return miembro;
    }
    //Endpoint para modificar a un usuario
    @PutMapping("/miembro/editar/{id}")
    public Miembro actualizarMiembro(@PathVariable Integer id, @RequestBody Miembro miembro) throws Exception {
        return interfazMiembro.modificarMiembro(id,miembro);
    }

    @GetMapping("miembro/calcularHuella/{miembroId}")
    public HashMap<Double, Double> calcularHuella(@PathVariable Integer miembroId, @RequestBody LocalDate fechaInicio, @RequestBody LocalDate fechaFin, @RequestBody Integer orgId) throws Exception {
        Double huella = CalculadoraHCMiembro.calcularHC(interfazMiembro.findMiembro(miembroId), fechaInicio, fechaFin, RepositorioOrganizaciones.getRepositorio().findOrganizacion(orgId));
        Double impacto = CalculadoraHCMiembro.calcularImpactoIndividual(interfazMiembro.findMiembro(miembroId),RepositorioOrganizaciones.getRepositorio().findOrganizacion(orgId), fechaInicio, fechaFin );
        HashMap<Double, Double> resultado = new HashMap<>();
        resultado.put(huella, impacto);
        return resultado;

        //Verificar si usar handlebars o no, ya que es carga a BD y visualizacion
    }

}
