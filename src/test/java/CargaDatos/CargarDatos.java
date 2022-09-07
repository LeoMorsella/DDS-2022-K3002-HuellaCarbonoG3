package CargaDatos;
import java.util.ArrayList;
import java.util.Calendar;

import utn.frba.huelladecarbono.model.MedioDeTransporte.*;
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
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.FactoresDeEmision;

public class CargarDatos {
    public static Ubicacion cargarUbicacion1(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY", "maipu", "100");
    }

    public static Ubicacion cargarUbicacion2(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY", "maipu", "200");
    }

    public static Ubicacion cargarUbicacion3(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY", "maipu", "300");
    }

    public static Ubicacion cargarUbicacion4(){
        return new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY", "maipu", "400");
    }

    public static Ubicacion cargarUbicacion5(){
        return new Ubicacion("Argentina","Buenos Aires","La Matanza","Gonzalez Catan","Matienzo","15500");
    }

    public static MedioMotorizado cargarMedioMotorizado1(){
        return new MedioMotorizado(TipoVehiculoMotorizado.AUTO, TipoCombustible.GASOIL, "AGE342", false, "Particular");
    }

    public static MedioNoMotorizado cargarMedioNoMotorizado1(){
        return new MedioNoMotorizado(TipoMedioNoMotorizado.BICI);
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

    public static ArrayList<Miembro> cargarContactosMail1(){
        return new ArrayList<>();
    }

    public static ArrayList<Miembro> cargarContactosWPP1(){
        return new ArrayList<>();
    }

    public static Organizacion cargarOrganizacion1(){
        Organizacion organizacion = new Organizacion("", TipoOrg.GUBERNAMENTAL,cargarUbicacion3(), Clasificacion.MINISTERIO, cargarContactosMail1(), cargarContactosWPP1());
        return organizacion;
    }

    public static Organizacion cargarOrganizacion5(){
        return new Organizacion("SA", TipoOrg.EMPRESA,cargarUbicacion5(), Clasificacion.MINISTERIO, null, null);
    }


    public static ArrayList<Area> cargarAreas1() {
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(cargarArea1());
        return areas;
    }

    public static Area cargarArea1() {
        Area area = new Area();
        ArrayList<DatoDeMedicion> mediciones = new ArrayList<>();
        mediciones.add(cargarDatoMedicion1());
        area.addMedicion(mediciones);
        area.addMiembro(cargarMiembros1());
        return area;
    }

    public static ArrayList<Miembro> cargarMiembros1(){
        ArrayList<Miembro> miembros = new ArrayList<>();
        miembros.add(cargarMiembro1());
        miembros.add(cargarMiembro2());
        return miembros;
    }

    public static DatoDeMedicion cargarDatoMedicion1(){
        return new DatoDeMedicion("Electricidad adquirida y consumida","m3","Electricidad","2000","Diaria","30");
    }

    public static Miembro cargarMiembro1(){
        return new Miembro("Pepe","","",12345, new ArrayList<>(), cargarRecorridos1());
    }

    public static Miembro cargarMiembro2(){
        return new Miembro("Juan","Perez","DNI",123456789,new ArrayList<Area>(), cargarRecorridos1());
    }

    public static ArrayList<Trayecto> cargarTrayectos1(){
        ArrayList<Trayecto> trayectos = new ArrayList<>();
        trayectos.add(cargarTrayecto1());
        trayectos.add(cargarTrayecto2());
        return trayectos;
    }

    public static Recorrido cargarRecorrido1(){
        Calendar mesInicio = Calendario.crearFecha(0,2021);
        Calendar mesFin = Calendario.crearFecha(0,2023);
        Recorrido recorrido = new Recorrido(cargarOrganizacion1(), 1.0, mesInicio, mesFin);
        recorrido.addTrayecto(cargarTrayecto1(), cargarOrganizacion1());
        recorrido.addTrayecto(cargarTrayecto2(), cargarOrganizacion1());
        recorrido.addTrayecto(cargarTrayecto3(), cargarOrganizacion1());
        return recorrido;
    }

    public static ArrayList<Recorrido> cargarRecorridos1(){
        ArrayList<Recorrido> lista = new ArrayList<>();
        lista.add(cargarRecorrido1());
        return lista;
    }

    public static void cargarFE() {
        FactoresDeEmision fe = FactoresDeEmision.getInstance();
        fe.setFE("AUTO",0.5);
        fe.setFE("BICI",0.0);
        fe.setFE("Electricidad adquirida y consumida", 0.1);
    }

    public static void cargarFE2() {
        FactoresDeEmision fe = FactoresDeEmision.getInstance();
        fe.setFE("AUTO",0.6);
        fe.setFE("BICI",0.0);
        fe.setFE("Electricidad adquirida y consumida", 0.4);
    }
}
