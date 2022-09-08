package CalculoHuellaTest;

import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.*;
import CargaDatos.CargarDatos;

public class CalculoHuellaTest {

    //Correcciones: Todavía faltan los valores de cada test
    //Se cargan muchos datos acá cuando se podrían poner en la clase de carga de datos
    //No se cargan los datos en el test de sector territorial

    @Test
    public void calcularHuellaMiembro() throws Exception {
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Ubicacion ubicacion = CargarDatos.cargarUbicacion1();
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        ArrayList<Miembro> miembros = new ArrayList<>();
        Organizacion organizacion = new Organizacion("Panini", TipoOrg.EMPRESA,ubicacion,Clasificacion.EMPRESA_SECTOR_PRIMARIO,miembros,miembros);
        Recorrido recorrido = new Recorrido(organizacion,1.0,mesInicio,mesFin);
        recorridos.add(recorrido);
        Miembro miembro = new Miembro("Juan","Perez","DNI",123456,areas,recorridos);
        miembros.add(miembro);

        Area area = new Area("CABA",organizacion);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin, organizacion);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test

    //Creeria que este test cumple
    public void calcularHuellaOrganizacion() throws Exception {
        //Carga de datos manual
        /*
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Ubicacion ubicacion = CargarDatos.cargarUbicacion1();
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        ArrayList<Miembro> miembros = new ArrayList<>();
        Organizacion organizacion = new Organizacion("Panini", TipoOrg.EMPRESA,ubicacion,Clasificacion.EMPRESA_SECTOR_PRIMARIO,miembros,miembros);
        Recorrido recorrido = new Recorrido(organizacion,1.0,mesInicio,mesFin);
        recorridos.add(recorrido);
        Miembro miembro = new Miembro("Juan","Perez","DNI",123456,areas,recorridos);
        miembros.add(miembro);

        Area area = new Area("CABA",organizacion);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);
*/
        //Carga de datos por cargaDatos

        Ubicacion ubicacion = CargarDatos.cargarUbicacion1();
        Area area = CargarDatos.cargarArea1();
        Recorrido recorrido = CargarDatos.cargarRecorrido1();
        Miembro miembro = CargarDatos.cargarMiembro1();
        Organizacion organizacion = CargarDatos.cargarOrganizacion1();
        Calendar mesInicio = Calendario.crearFecha(10, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);

        for(Area area1 : miembro.getAreas()){
            area1.setMiembros(organizacion.getContactosMail());
        }
        organizacion.setAreas(miembro.getAreas());

        CargarDatos.cargarFE();


        organizacion = CargarDatos.cargarOrganizacion1();
        organizacion.addArea(CargarDatos.cargarArea1());

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, mesInicio, mesFin);

        //Assert.assertEquals(Optional.of(1.1), huella);
        Assertions.assertEquals(16.666666666666668,huella);
        //Assert.assertNotEquals(Optional.of(0.0), huella);
    }

    @Test
    public void calcularHuellaArea() throws Exception {
/*
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Ubicacion ubicacion = CargarDatos.cargarUbicacion1();
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        ArrayList<Miembro> miembros = new ArrayList<>();
        Organizacion organizacion = new Organizacion("Panini", TipoOrg.EMPRESA,ubicacion,Clasificacion.EMPRESA_SECTOR_PRIMARIO,miembros,miembros);
        Recorrido recorrido = new Recorrido(organizacion,1.0,mesInicio,mesFin);
        recorridos.add(recorrido);
        Miembro miembro = new Miembro("Juan","Perez","DNI",123456,areas,recorridos);
        miembros.add(miembro);

        Area area = new Area("CABA",organizacion);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);
*/

        Ubicacion ubicacion = CargarDatos.cargarUbicacion2();
        Area area = CargarDatos.cargarArea1();
        Recorrido recorrido = CargarDatos.cargarRecorrido1();
        Miembro miembro = CargarDatos.cargarMiembro2();
        Organizacion organizacion = CargarDatos.cargarOrganizacion1();
        Calendar mesInicio = Calendario.crearFecha(10, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);

        for(Area area1 : miembro.getAreas()){
            area1.setMiembros(area.getMiembros());
        }

        //El cambio de valor final se vasa en los datos que carguemos dentro del CargaFE
        CargarDatos.cargarFE2();

        area = CargarDatos.cargarArea1();

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCArea(area, mesInicio, mesFin);

        //Assert.assertEquals(Optional.of(1.1),huella);
        Assertions.assertEquals(49.99999999999999,huella);
    }

    //No toma los datos de organizacioon
    @Test
    public void calcularHuellaSectorTerritorial() throws Exception{
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        SectorTerritorial sector = CargarDatos.cargarSector1();
        Organizacion organizacion = CargarDatos.cargarOrganizacion1();
        Organizacion organizacion2 = CargarDatos.cargarOrganizacion5();
        sector.getOrganizaciones().add(organizacion);
        sector.getOrganizaciones().add(organizacion2);

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, mesInicio, mesFin);
    
        Assert.assertEquals(Optional.of(1.1),huella);
    }

    //Existe un error en la funcion de organizacion
    @Test
    public void calcularImpactoIndividualOrganizacion() throws Exception{
        /*Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Ubicacion ubicacion = CargarDatos.cargarUbicacion1();
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        ArrayList<Miembro> miembros = new ArrayList<>();
        Organizacion organizacion = new Organizacion("Panini", TipoOrg.EMPRESA,ubicacion,Clasificacion.EMPRESA_SECTOR_PRIMARIO,miembros,miembros);
        Recorrido recorrido = new Recorrido(organizacion,1.0,mesInicio,mesFin);
        recorridos.add(recorrido);
        Miembro miembro = new Miembro("Juan","Perez","DNI",123456,areas,recorridos);
        miembros.add(miembro);

        Area area = new Area("CABA",organizacion);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);

*/
        Ubicacion ubicacion = CargarDatos.cargarUbicacion2();
        Area area = CargarDatos.cargarArea1();
        Recorrido recorrido = CargarDatos.cargarRecorrido1();
        Miembro miembro = CargarDatos.cargarMiembro2();
        Organizacion organizacion = CargarDatos.cargarOrganizacion1();
        Calendar mesInicio = Calendario.crearFecha(10, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        CargarDatos.cargarFE();
        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCPromedio(miembro,organizacion,mesInicio,mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaImpactoIndividual() throws Exception{
        /*Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Ubicacion ubicacion = CargarDatos.cargarUbicacion1();
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Recorrido> recorridos = new ArrayList<>();
        ArrayList<Miembro> miembros = new ArrayList<>();
        Organizacion organizacion = new Organizacion("Panini", TipoOrg.EMPRESA,ubicacion,Clasificacion.EMPRESA_SECTOR_PRIMARIO,miembros,miembros);
        Recorrido recorrido = new Recorrido(organizacion,1.0,mesInicio,mesFin);
        recorridos.add(recorrido);
        Miembro miembro = new Miembro("Juan","Perez","DNI",123456,areas,recorridos);
        miembros.add(miembro);

        Area area = new Area("CABA",organizacion);
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);
*/

        Ubicacion ubicacion = CargarDatos.cargarUbicacion2();
        Area area = CargarDatos.cargarArea1();
        Recorrido recorrido = CargarDatos.cargarRecorrido1();
        Miembro miembro = CargarDatos.cargarMiembro2();
        Organizacion organizacion = CargarDatos.cargarOrganizacion1();
        Calendar mesInicio = Calendario.crearFecha(10, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        CargarDatos.cargarFE();
        Double res = 0.0;

        for(Area area1 : miembro.getAreas()){
            area1.setMiembros(recorrido.getOrganizacion().getContactosMail());
        }
        organizacion.setAreas(miembro.getAreas());
        for(Miembro miembro1 : organizacion.getMiembros()){
            res += CalculadoraHCService.getCalculadoraHC().calcularImpactoIndividual(miembro1, organizacion, mesInicio, mesFin);
        }

        Assert.assertEquals(Optional.of(1.1), res);
    }
}
