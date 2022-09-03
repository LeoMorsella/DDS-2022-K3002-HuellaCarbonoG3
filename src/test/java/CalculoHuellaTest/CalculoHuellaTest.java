package CalculoHuellaTest;

import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import utn.frba.huelladecarbono.model.MedioDeTransporte.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.*;
import CargaDatos.CargarDatos;

public class CalculoHuellaTest {

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

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaOrganizacion() throws Exception {
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



        for(Area area1 : areas){
            area1.setMiembros(miembros);
        }
        organizacion.setAreas(areas);

        CargarDatos.cargarFE();


        organizacion = CargarDatos.cargarOrganizacion1();
        organizacion.addArea(CargarDatos.cargarArea1());

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1), huella);
        //Assert.assertNotEquals(Optional.of(0.0), huella);
    }

    @Test
    public void calcularHuellaArea() throws Exception {

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


        for(Area area1 : areas){
            area1.setMiembros(miembros);
        }

        CargarDatos.cargarFE();

        area = CargarDatos.cargarArea1();

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCArea(area, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    //No calcula respeecto a nada porque esta vacío el repo de organizaciones
    @Test
    public void calcularHuellaSectorTerritorial() throws Exception{
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        SectorTerritorial sector = CargarDatos.cargarSector1();

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, mesInicio, mesFin);
    
        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaPromedio() throws Exception{
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

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCPromedio(miembro,organizacion,mesInicio,mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaImpactoIndividual() throws Exception{
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

        Double res = 0.0;

        for(Area area1 : areas){
            area1.setMiembros(miembros);
        }
        organizacion.setAreas(areas);
        for(Miembro miembro1 : organizacion.getMiembros()){
            res += CalculadoraHCService.getCalculadoraHC().calcularImpactoIndividual(miembro1, organizacion, mesInicio, mesFin);
        }

        Assert.assertEquals(Optional.of(1.1), res);
    }
}
