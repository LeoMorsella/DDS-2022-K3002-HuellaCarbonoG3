package utn.frba.huelladecarbono;
import java.util.ArrayList;
import java.util.Calendar;

import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.AgenteSectorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

public class CargaDatos {
    public static Ubicacion cargarUbicacion1(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
    }

    public static Ubicacion cargarUbicacion2(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "200");
    }

    public static Ubicacion cargarUbicacion3(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "300");
    }

    public static Ubicacion cargarUbicacion4(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "400");
    }

    public static Ubicacion cargarUbicacion5(){
        return new Ubicacion("Argentina","Buenos Aires","La Matanza","Gonzalez Catan","Matienzo","15500");
    }

    public static MedioMotorizado cargarMedioMotorizado1(){
        return new MedioMotorizado();
    }

    public static MedioNoMotorizado cargarMedioNoMotorizado1(){
        return new MedioNoMotorizado();
    }

    public static Trayecto cargarTrayecto1(){
        return new Trayecto(cargarUbicacion1(), cargarUbicacion2(), cargarMedioMotorizado1());
    }

    public static Trayecto cargarTrayecto2(){
        return new Trayecto(cargarUbicacion2(), cargarUbicacion3(), cargarMedioNoMotorizado1());
    }

    public static Trayecto cargarTrayecto3(){
        return new Trayecto(cargarUbicacion3(), cargarUbicacion4(), cargarMedioNoMotorizado1());
    }

    public static SectorTerritorial cargarSector1(){
        return new SectorTerritorial(1, "MONTECARLO", "MISIONES", cargarAgente1());
    }

    public static AgenteSectorial cargarAgente1(){
        return new AgenteSectorial();
    }

    public static ArrayList<Area> cargarAreasVacias(){
        return new ArrayList<>();
    }

    public static ArrayList<Miembro> cargarContactosMail1(){
        return new ArrayList<>();
    }

    public static ArrayList<Miembro> cargarContactosWPP1(){
        return new ArrayList<>();
    }

    public static Organizacion cargarOrganizacion1(){
        return new Organizacion("", TipoOrg.GUBERNAMENTAL,cargarUbicacion3(), cargarAreasVacias(), Clasificacion.MINISTERIO, cargarContactosMail1(), cargarContactosWPP1());
    }

    public static Organizacion cargarOrganizacion5(){
        return new Organizacion("SA", TipoOrg.EMPRESA,cargarUbicacion5(),cargarAreasVacias(), Clasificacion.MINISTERIO, null, null);
    }

    public static ArrayList<Miembro> cargarMiembros1(){
        return new ArrayList<>();
    }

    public static Area cargarArea1(){
        return new Area("nombre del area", cargarMiembros1(), cargarOrganizacion1());
    }

    public static Miembro cargarMiembro1(){
        return new Miembro("algo","","",12345, cargarAreasVacias(), cargarRecorridos1());
    }

    public static DatoDeMedicion cargarDatoMedicion1(){
        return new DatoDeMedicion("Electricidad adquirida y consumida","m3","Electricidad","2000","Diaria","30");
    }

    public static Miembro cargarMiembro2(){
        return new Miembro("Juan","Perez","DNI",123456789,cargarAreasVacias(),cargarRecorridosVacios());
    }

    public static ArrayList<Trayecto> cargarTrayectos1(){
        ArrayList<Trayecto> lista = new ArrayList<>();
        lista.add(cargarTrayecto1());
        lista.add(cargarTrayecto2());
        return lista;
    }

    public static ArrayList<Recorrido> cargarRecorridosVacios(){
        return new ArrayList<>();
    }

    public static Recorrido cargarRecorrido1(){
        Calendar mesInicio = Calendario.crearFecha(0,2022);
        Calendar mesFin = Calendario.sinFecha();
        return new Recorrido(cargarOrganizacion1(), 1.0, mesInicio, mesFin);
    }

    public static ArrayList<Recorrido> cargarRecorridos1(){
        ArrayList<Recorrido> lista = new ArrayList<>();
        lista.add(cargarRecorrido1());
        return lista;
    }
}
