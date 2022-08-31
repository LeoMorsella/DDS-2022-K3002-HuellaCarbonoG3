package OrganizacionTest;

import utn.frba.huelladecarbono.CargaDatos;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MiembroTest {
    @Test

    public void agregarAreaCorrectamente() {
        Organizacion organizacionPrueba = CargaDatos.cargarOrganizacion5();
        Miembro miembro = CargaDatos.cargarMiembro2();
        ArrayList<Miembro> miembrosPrueba = new ArrayList<>();
        miembrosPrueba.add(miembro);
        Area areaPrueba = new Area("AreaPrueba",miembrosPrueba,organizacionPrueba);
        miembro.registrarseA(areaPrueba);
        Assertions.assertEquals(1,miembro.getAreas().size());

    }

    @Test
    public void medirImpactoPersonal() throws Exception {
        Organizacion organizacionPrueba = CargaDatos.cargarOrganizacion5();
        Miembro miembro = CargaDatos.cargarMiembro2();
        DatoDeMedicion datoDeMedicion = CargaDatos.cargarDatoMedicion1();
        ArrayList<DatoDeMedicion> mediciones = new ArrayList<>();
        mediciones.add(datoDeMedicion);
        // miembro.setMediciones(medicion); // TODO el miembro tiene mediciones?
        Assertions.assertEquals(2000,miembro.calcularImpactoIndividual(organizacionPrueba));
    }
}
