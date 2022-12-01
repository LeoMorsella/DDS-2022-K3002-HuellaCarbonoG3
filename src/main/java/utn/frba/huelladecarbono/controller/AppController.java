package utn.frba.huelladecarbono.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.apache.poi.ss.formula.functions.Areas;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.service.HandleBars;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    private Handlebars handlebars = HandleBars.getHandleBars();
    @GetMapping({"/login", "/", "/index", "/login.html"})
    public String login() {return "login";}

    //Rutas de vistas de miembro

    @GetMapping({"/miembro/calcularHuella", "/miembro/calcularHuella.html"})
    public String calcularHuellaM() {
        return "calcular-huellaMiembro";
    }

    @GetMapping({"/miembro/datosPersonales", "/miembro/datos-personales.html"})
    public String datosPersonalesM(){return "datos-PersonalesMiembro";}

    @GetMapping({"/miembro/organizaciones", "/miembro/organizaciones.html"})
    public String organizacionesM(){return "organizacionesMiembro";}

    @GetMapping({"/miembro/recorridos", "/miembro/recorridos.html"})
    public String recorridosM(){return "recorridosMiembro";}

    //Rutas de vistas de organizacion

    @GetMapping(value="/{idOrganizacion}/areas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> areas(@PathVariable String idOrganizacion) throws IOException {

        Template template = handlebars.compile("/templates/areasOrganizacion");
        List<Area> areas = RepositorioOrganizaciones
                .getRepositorio()
                .findOrganizacion(Integer.parseInt(idOrganizacion))
                .getAreas();

        Map<String, Object> model = new HashMap<>();
        model.put("area", areas);
        model.put("organizacionID", idOrganizacion);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    @GetMapping({"/organizacion/calcularHuella", "/organizacion/calcular-huella.html"})
    public String calcularHuellaO(){return "calcular-huellaOrganizacion";}

    @GetMapping({"/organizacion/contactos", "/organizacion/contactos.html"})
    public String contactosO(){return "contactosOrganizacion";}

    @GetMapping({"/organizacion/datosDeActividad", "/organizacion/datos-de-actividad.html"})
    public String datosDeActividadO(){return "datos-de-actividadOrganizacion";}

    @GetMapping({"/organizacion/datosInternos", "/organizacion/datos-internos.html"})
    public String datosInternosO(){return "datos-internosOrganizacion";}

    @GetMapping({"/organizacion/miembros", "/organizacion/miembros.html"})
    public String miembrosO(){return "miembrosOrganizacion";}

    @GetMapping({"/organizacion/recomendaciones", "/organizacion/recomendaciones.html"})
    public String recomendacionesO(){return "recomendacionesOrganizacion";}

    @GetMapping({"/organizacion/recorridos", "/organizacion/recorridos.html"})
    public String recorridosO(){return "recorridosOrganizacion";}

    @GetMapping({"/organizacion/reportes", "/organizacion/reportes.html"})
    public String reportesO(){return "reportesOrganizacion";}

    // rutas de vistas de Agente Sectorial

    @GetMapping({"/AS/reportes", "/AS/reportes.html"})
    public String reportesAS(){return "reportesAS";}

    @GetMapping({"/AS/recomendaciones", "/AS/recomendaciones.html"})
    public String recomendacionesAS(){return "recomendacionesAS";}
}
