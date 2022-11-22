package utn.frba.huelladecarbono.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"/recomendaciones"})
    public String recomendaciones() {return "recomendaciones.html";}

    @GetMapping({"/login", "/", "/index"})
    public String login() {return "login";}

    //Rutas de vistas de miembro

    @GetMapping({"/miembro/calcularHuella"})
    public String calcularHuellaM(){return "calcular-huellaMiembro";}

    @GetMapping({"/miembro/datosPersonales"})
    public String datosPersonalesM(){return "datos-PersonalesMiembro";}

    @GetMapping({"/miembro/organizaciones"})
    public String organizacionesM(){return "organizacionesMiembro";}

    @GetMapping({"/miembro/recorridos"})
    public String recorridosM(){return "recorridosMiembro";}

    //Rutas de vistas de organizacion

    @GetMapping({"/organizacion/areas"})
    public String areas(){return "areasOrganizacion";}

    @GetMapping({"/organizacion/calcularHuella"})
    public String calcularHuellaO(){return "calcular-huellaOrganizacion";}

    @GetMapping({"/organizacion/contactos"})
    public String contactosO(){return "contactosOrganizacion";}

    @GetMapping({"/organizacion/datosDeActividad"})
    public String datosDeActividadO(){return "datos-de-actividadOrganizacion";}

    @GetMapping({"/organizacion/datosInternos"})
    public String datosInternosO(){return "datos-internosOrganizacion";}

    @GetMapping({"/organizacion/miembros"})
    public String miembrosO(){return "miembrosOrganizacion";}

    @GetMapping({"/organizacion/recomendaciones"})
    public String recomendacionesO(){return "recomendacionesOrganizacion";}

    @GetMapping({"/organizacion/recorridos"})
    public String recorridosO(){return "recorridosOrganizacion";}

    @GetMapping({"/organizacion/reportes"})
    public String reportesO(){return "reportesOrganizacion";}

    // rutas de vistas de Agente Sectorial

    @GetMapping({"/AS/reportes"})
    public String reportesAS(){return "reportesAS";}

    @GetMapping({"/AS/recomendaciones"})
    public String recomendacionesAS(){return "recomendacionesAS";}
}
