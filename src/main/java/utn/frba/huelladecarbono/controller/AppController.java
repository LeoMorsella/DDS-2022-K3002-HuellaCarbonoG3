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

    //Rutas de vistas de miembro


    @GetMapping({"calcularHuella", "calcularHuella.html"})
    public String calcularHuellaM() {
        return "MiembroCalculadora";
    }

    @GetMapping({"/datosPersonales", "/miembro/datos-personales.html"})
    public String datosPersonalesM(){return "MiembroDatosPersonales";}

    @GetMapping({"/organizaciones", "/organizaciones.html"})
    public String organizacionesM(){return "MiembroOrganizaciones";}

    @GetMapping({"/recorridos", "/recorridos.html"})
    public String recorridosM(){return "MiembroRecorridos";}

    @GetMapping({"/orgMiembros", "/orgMiembros.html"})
    public String orgMiembros(){return "orgMiembros";}


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

    @GetMapping({"/organizacion/calcularHuella", "/organizacion/calcular-huella.html"})
    public String calcularHuellaO(){return "OrgCalculadora";}

    @GetMapping({"/organizacion/contactos", "/organizacion/contactos.html"})
    public String contactosO(){return "OrgContactos";}

    @GetMapping({"/organizacion/datosDeActividad", "/organizacion/datos-de-actividad.html"})
    public String datosDeActividadO(){return "OrgDatosDeActividad";}

    @GetMapping({"/organizacion/datosInternos", "/organizacion/datos-internos.html"})
    public String datosInternosO(){return "OrgDatosInternos";}

    @GetMapping({"/organizacion/miembros", "/organizacion/miembros.html"})
    public String miembrosO(){return "OrgMiembros";}

    @GetMapping({"/organizacion/recomendaciones", "/organizacion/recomendaciones.html","/organizacion/orgRecomendaciones"})
    public String recomendacionesO(){return "OrgRecomendaciones";}

    @GetMapping({"/organizacion/recorridos", "/organizacion/recorridos.html"})
    public String recorridosO(){return "OrgRecorridos";}

    @GetMapping({"/organizacion/reportes", "/organizacion/reportes.html"})
    public String reportesO(){return "OrgReportes";}
    @GetMapping({"orgRecomendaciones", "orgRecomendaciones.html"})
    public String orgRecomendaciones(){return "OrgRecomendaciones";}

    // rutas de vistas de Agente Sectorial

    @GetMapping({"/AS/reportes", "/AS/reportes.html"})
    public String reportesAS(){return "AgenteReportes";}

    @GetMapping({"/AS/recomendaciones", "/AS/recomendaciones.html"})
    public String recomendacionesAS(){return "AgenteRecomendaciones";}

}
