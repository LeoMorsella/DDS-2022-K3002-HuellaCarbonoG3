package APIMedicionesTests;

import utn.frba.huelladecarbono.model.CalculoDeHuella.FactoresDeEmision;
import utn.frba.huelladecarbono.model.CargaDeMediciones.CargaDeMediciones;
import utn.frba.huelladecarbono.model.CargaDeMediciones.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ManejoAmbiental.*;
import utn.frba.huelladecarbono.model.ManejoAmbiental.*;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoMedioNoMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoVehiculoMotorizado;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.model.ManejoAmbiental.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PruebaLecturaArchivo {
    FactoresDeEmision FE = FactoresDeEmision.getInstance();
    ArrayList<Area> areas = new ArrayList<Area>();
    ArrayList<Miembro> miembros = new ArrayList<Miembro>();
    ArrayList<Trayecto> trayectos = new ArrayList<Trayecto>();
    ArrayList<Recorrido> recorridos = new ArrayList<>();

    Ubicacion ubicacion1 = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
    Ubicacion ubicacion2 = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "200");
    Ubicacion ubicacion3 = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "300");
    MedioMotorizado medio1 = new MedioMotorizado();
    MedioNoMotorizado medio2 = new MedioNoMotorizado();
    Trayecto trayecto1 = new Trayecto(ubicacion1,ubicacion2, medio1, 0.2);
    Trayecto trayecto2 = new Trayecto(ubicacion2,ubicacion3, medio2, 0.1);
    Recorrido recorrido = null;

    Organizacion organizacion = new Organizacion("", TipoOrg.GUBERNAMENTAL,ubicacion3,areas, Clasificacion.MINISTERIO,miembros,miembros);
    Area area = new Area("nombre del area",miembros,organizacion);
    Miembro miembro = new Miembro("algo","","",12345, areas, recorridos);

    @Test
    public void testCargarDatosALista() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones1.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(1400.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosALista2() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones2.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(750.0,listaDatoDeMedicion.get(3).getValor());
    }

    @Test
    public void testCargarDatosALista3() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones3.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(1564.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosALista4() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones4.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(750.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosALista5() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones5.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(1450.0,listaDatoDeMedicion.get(2).getValor());
    }


    @Test
    public void calcularHuellaMiembro() {
        FE.setFE("AUTO",0.5);
        FE.setFE("BICI",0.0);
        medio1.setTipo(TipoVehiculoMotorizado.AUTO);
        medio2.setTipo(TipoMedioNoMotorizado.BICI);
        areas.add(area);
        miembros.add(miembro);
        trayectos.add(trayecto1);
        trayectos.add(trayecto2);
        recorrido = Recorrido.nuevoRecorrido(trayectos, 5.0);
        System.out.println(recorrido.getFactorDeUso());
        recorridos.add(recorrido);
        System.out.println(FE.getFE("AUTO"));

        Double huella = miembro.calcularHC();

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaOrganizacion() throws Exception {
        trayectos.add(trayecto1);
        trayectos.add(trayecto2);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);

        Double huella = organizacion.calcularHC();

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaArea() throws Exception {
        trayectos.add(trayecto1);
        trayectos.add(trayecto2);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);

        Double huella = area.calcularHC();

        Assert.assertEquals(Optional.of(1.1),huella);
    }
}










