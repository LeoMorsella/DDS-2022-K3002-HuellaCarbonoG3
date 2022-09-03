package CalculoHuellaTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import utn.frba.huelladecarbono.service.CalculoDeHuellaService.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.CargaDatos;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoMedioNoMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoVehiculoMotorizado;

public class CalculoHuellaTest {

    FactoresDeEmision FE = FactoresDeEmision.getInstance();
    ArrayList<Area> areas = CargaDatos.cargarAreas1();
    ArrayList<Miembro> miembros = CargaDatos.cargarMiembros1();
    ArrayList<Trayecto> trayectos = CargaDatos.cargarTrayectos1();
    ArrayList<Recorrido> recorridos = CargaDatos.cargarRecorridos1();
    MedioMotorizado medio1 = CargaDatos.cargarMedioMotorizado1();
    MedioNoMotorizado medio2 = CargaDatos.cargarMedioNoMotorizado1();
    Recorrido recorrido = CargaDatos.cargarRecorrido1();

    Organizacion organizacion = CargaDatos.cargarOrganizacion1();
    Area area = CargaDatos.cargarArea1();
    Miembro miembro = CargaDatos.cargarMiembro1();

    SectorTerritorial sector = CargaDatos.cargarSector1();

    @Test
    public void calcularHuellaMiembro() throws Exception {
        FE.setFE("AUTO",0.5);
        FE.setFE("BICI",0.0);
        medio1.setTipo(TipoVehiculoMotorizado.AUTO);
        medio2.setTipo(TipoMedioNoMotorizado.BICI);
        areas.add(area);
        miembros.add(miembro);
        recorridos.add(recorrido);
        System.out.println(FE.getFE("AUTO"));
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaOrganizacion() throws Exception {
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);
        for(Area area : areas){
            area.setMiembros(miembros);
        }
        organizacion.setAreas(areas);
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1), huella);
        //Assert.assertNotEquals(Optional.of(0.0), huella);
    }

    @Test
    public void calcularHuellaArea() throws Exception {
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);
        for(Area area : areas){
            area.setMiembros(miembros);
        }
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCArea(area, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaSectorTerritorial() throws Exception{
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, mesInicio, mesFin);
    
        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaPromedio() throws Exception{
        
    }

    @Test
    public void calcularHuellaImpactoIndividual() throws Exception{
        Double res = 0.0;
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        for(Area area : areas){
            area.setMiembros(miembros);
        }
        organizacion.setAreas(areas);
        for(Miembro miembro : organizacion.getMiembros()){
            res += CalculadoraHCService.getCalculadoraHC().calcularImpactoIndividual(miembro, organizacion, mesInicio, mesFin);
        }

        Assert.assertEquals(Optional.of(1.1), res);
    }
}
