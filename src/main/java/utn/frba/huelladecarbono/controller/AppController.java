package utn.frba.huelladecarbono.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"/login", "/", "/index", "/login.html"})
    public String login() {return "login";}

    //Rutas de vistas de miembro

    @GetMapping({"/miembro/calcularHuella", "/miembro/calcular-huella.html"})
    public String calcularHuellaM(){return "calcular-huellaMiembro";}

    @GetMapping({"/miembro/datosPersonales", "/miembro/datos-personales.html"})
    public String datosPersonalesM(){return "datos-PersonalesMiembro";}

    @GetMapping({"/miembro/organizaciones", "/miembro/organizaciones.html"})
    public String organizacionesM(){return "organizacionesMiembro";}

    @GetMapping({"/miembro/recorridos", "/miembro/recorridos.html"})
    public String recorridosM(){return "recorridosMiembro";}

    //Rutas de vistas de organizacion

    @GetMapping({"/organizacion/areas", "/organizacion/areas.html"})
    public String areas(){return "areasOrganizacion";}

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
