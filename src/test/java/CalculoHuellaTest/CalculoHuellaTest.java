package CalculoHuellaTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import utn.frba.huelladecarbono.service.CalculoDeHuellaService.*;
import utn.frba.huelladecarbono.service.CargaDeMedicionesService.*;
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
    ArrayList<Area> areas = CargaDatos.cargarAreasVacias();
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
    public void calcularHuellaMiembro() {
        FE.setFE("AUTO",0.5);
        FE.setFE("BICI",0.0);
        medio1.setTipo(TipoVehiculoMotorizado.AUTO);
        medio2.setTipo(TipoMedioNoMotorizado.BICI);
        areas.add(area);
        miembros.add(miembro);
        recorridos.add(recorrido);
        System.out.println(FE.getFE("AUTO"));

        Double huella = miembro.calcularHC();

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaOrganizacion() throws Exception {
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);

        Double huella = organizacion.calcularHC();

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaArea() throws Exception {
        areas.add(area);
        recorridos.add(recorrido);
        miembros.add(miembro);

        Double huella = area.calcularHC();

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaSectorTerritorial() throws Exception{
        //Double huella = sector.calcularHC();

        //Assert.assertEquals(Optional.of(1.1), huella);
    }

    @Test
    public void calcularHuellaPromedio() throws Exception{
        
    }
}
