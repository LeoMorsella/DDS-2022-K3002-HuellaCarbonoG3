package InformesTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utn.frba.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioSectorTerritorial;
import utn.frba.huelladecarbono.service.InformesService.ObtenerDatosInformes;

public class InformesTest {

    CreadorDeObjetos creadorDeObjetos;
    AgenteSectorial agente = new AgenteSectorial();
    SectorTerritorial sectorTerritorialprueba = new SectorTerritorial(1,"Gonzalez Catan","Buenos Aires",agente);
    SectorTerritorial sectorTerritorialprueba2 = new SectorTerritorial(2,"Isidro Casanova","Buenos Aires",agente);
    ObtenerDatosInformes obtenerDato = new ObtenerDatosInformes();
/*
    Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
    Organizacion organizacion2 = new Organizacion ("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
    Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
    Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);
*/

    @Test
    public void testHCSector() {
        sectorTerritorialprueba.setHC(25.0);
        sectorTerritorialprueba2.setHC(50.0);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba2);
        String huella = obtenerDato.hcTotalPorST();
        System.out.println(huella);
        Assertions.assertTrue(huella != "");
    }

    @Test
    public void testHCSectorEspecifico() {
        sectorTerritorialprueba.setHC(25.0);
        sectorTerritorialprueba2.setHC(50.0);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba2);
        String huella = obtenerDato.hcTotalDeST(sectorTerritorialprueba);
        System.out.println(huella);
        Assertions.assertTrue(huella != "");
    }

    @Test
    public void testHCPais() {
        sectorTerritorialprueba.setHC(25.0);
        sectorTerritorialprueba2.setHC(50.0);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba2);
        String huella = obtenerDato.hcTotalPais();
        System.out.println(huella);
        Assertions.assertTrue(huella != "");
    }
/*
    @Test
    public void testHCOrganizacion() {
        organizacion1.setHC(25.0);
        organizacion2.setHC(50.0);
        organizacion3.setHC(5.0);
        organizacion4.setHC(75.0);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion1);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion2);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion3);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion4);
        String huella = obtenerDato.hcTotalPorTipoDeOrg();
        System.out.println(huella);
        Assertions.assertTrue(huella != "");

    }

    @Test
    public void testHCOrganizacionEspecifica() {
        organizacion1.setHC(25.0);
        organizacion1.getHuellaTotal();
        organizacion2.setHC(50.0);
        organizacion3.setHC(5.0);
        organizacion4.setHC(75.0);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion1);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion2);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion3);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion4);
        String huella = obtenerDato.hcDeOrganizacion(organizacion4);
        System.out.println(huella);
        Assertions.assertTrue(huella != "");

    }
*/



}
