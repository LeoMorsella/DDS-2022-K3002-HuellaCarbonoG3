package utn.frba.huelladecarbono.controller;

import com.github.jknack.handlebars.Handlebars;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.service.HandleBars;

@Controller
public class AppController {

    private Handlebars handlebars = HandleBars.getHandleBars();

    @GetMapping({"/login", "/", "/index"})
    public String login() {return "login";}




    @GetMapping("/miembro/calcularHuella")
    public String calcularHuellaM() {return "MiembroCalculadora";}
    @GetMapping("/miembro/datosPersonales")

    public String datosPersonalesM(){return "MiembroDatosPersonales";}

    @GetMapping("/miembro/organizaciones")
    public String organizacionesM(){return "MiembroOrganizaciones";}

    @GetMapping("/miembro/recorridos")
    public String recorridosM(){return "MiembroRecorridos";}




    //Rutas de vistas de organizacion
/*
    @GetMapping(value="/{idOrganizacion}/areas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> areas(@PathVariable String idOrganizacion) throws Exception {

        Template template = handlebars.compile("/templates/OrgAreas");

        //despues borrar cuando funcione lo de los repos en memoria
        //usar ID de organizacion 1 y 2 para probar

        Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
        Organizacion organizacion2 = new Organizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
        Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
        Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);
        Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
        ArrayList<Double> listaHCPrueba = new ArrayList<>();
        Area area1 = new Area("AreaPrueba1", organizacion1);
        Area area2 = new Area("AreaPrueba2", organizacion1);
        organizacion2.setArea(area1);
        listaHCPrueba.add(100.00);
        HuellaCarbono huellaPrueba = new HuellaCarbono(Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021), 250.00);
        List<HuellaCarbono> hashMapPrueba = new ArrayList<>();
        hashMapPrueba.add(huellaPrueba);
        organizacion1.setID(1);
        organizacion2.setID(2);
        RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion2);
        RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion1);

        //borrar arriba

        List<Area> areas = RepositorioOrganizaciones
                .getRepositorio()
                .findOrganizacion(Integer.parseInt(idOrganizacion))
                .getAreas();

        Map<String, Object> model = new HashMap<>();
        model.put("area", areas);
        model.put("organizacionID", idOrganizacion);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }*/

    // ORGANIZACIÓN
    @GetMapping("organizacion/miembros")
    public String orgMiembros(){return "orgMiembros";}

    @GetMapping("/organizacion/calcularHuella")
    public String calcularHuellaO(){return "OrgCalculadora";}

    @GetMapping("/organizacion/contactos")
    public String contactosO(){return "OrgContactos";}

    @GetMapping("/organizacion/datosDeActividad")
    public String datosDeActividadO(){return "OrgDatosDeActividad";}

    @GetMapping("/organizacion/datosInternos")
    public String datosInternosO(){return "OrgDatosInternos";}

    @GetMapping("/organizacion/recomendaciones")
    public String recomendacionesO(){return "OrgRecomendaciones";}

    @GetMapping("/organizacion/recorridos")
    public String recorridosO(){return "OrgRecorridos";}

    @GetMapping("/organizacion/reportes")
    public String reportesO(){return "OrgReportes";}

    @GetMapping({"/organizacion/areas"})
    public String orgAreas(){return "OrgAreas";}


    // AGENTE SECTORIAL

    @GetMapping("/AS/reportes")
    public String reportesAS(){return "AgenteReportes";}

    @GetMapping("/AS/recomendaciones")
    public String recomendacionesAS(){return "AgenteRecomendaciones";}

}
