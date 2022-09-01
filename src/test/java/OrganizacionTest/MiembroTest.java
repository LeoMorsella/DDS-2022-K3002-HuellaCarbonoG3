package OrganizacionTest;

import utn.frba.huelladecarbono.CargaDatos;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MiembroTest {
    @Test
    public void agregarAreaCorrectamente() {
        Organizacion organizacionPrueba = CargaDatos.cargarOrganizacion5();
        Miembro miembro = CargaDatos.cargarMiembro2();
        ArrayList<Miembro> miembrosPrueba = new ArrayList<>();
        miembrosPrueba.add(miembro);
        Area areaPrueba = new Area("AreaPrueba",organizacionPrueba);
        miembro.registrarseA(areaPrueba);
        Assertions.assertEquals(1,miembro.getAreas().size());

    }

    @Test
    public void medirImpactoPersonal() throws Exception {
        Organizacion organizacionPrueba = CargaDatos.cargarOrganizacion1();
        Miembro miembro = CargaDatos.cargarMiembro2();
        miembro.getAreas().get(0).setMiembro(miembro);
        organizacionPrueba.setArea(miembro.getAreas().get(0));
        DatoDeMedicion datoDeMedicion = CargaDatos.cargarDatoMedicion1();
        ArrayList<DatoDeMedicion> mediciones = new ArrayList<>();
        mediciones.add(datoDeMedicion);
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(5, 2022);
        Assertions.assertEquals(2.6, CalculadoraHCService.getCalculadoraHC().calcularImpactoIndividual(miembro, organizacionPrueba, mesInicio, mesFin));
        //TODO el test llega hasta el final pero no se calcula correctamente el impacto
    }
}
