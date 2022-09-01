package APIDistanciaTests;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.CargaDatos;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import org.junit.Test;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DistanciasTest {

    @Test
    public void testDevuelveDistancia() throws Exception {
        Trayecto trayectoPrueba1 = CargaDatos.cargarTrayecto1();
        Trayecto trayectoPrueba2 = CargaDatos.cargarTrayecto3();

        Recorrido recorridoPrueba = CargaDatos.cargarRecorrido1();
        System.out.println(trayectoPrueba1.distanciaMedia());
        System.out.println(trayectoPrueba2.distanciaMedia());

        Organizacion org = recorridoPrueba.getOrganizacion();
        recorridoPrueba.addTrayecto(trayectoPrueba1, 0.0, org);

        System.out.println(recorridoPrueba.distanciaTotal());

    }

}
