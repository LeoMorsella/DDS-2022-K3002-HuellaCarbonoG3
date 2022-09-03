package APIDistanciaTests;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import CargaDatos.CargarDatos;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import org.junit.Test;

public class DistanciasTest {

    @Test
    public void testDevuelveDistancia() throws Exception {
        Trayecto trayectoPrueba1 = CargarDatos.cargarTrayecto1();
        Trayecto trayectoPrueba2 = CargarDatos.cargarTrayecto3();

        Recorrido recorridoPrueba = CargarDatos.cargarRecorrido1();
        System.out.println(trayectoPrueba1.distanciaMedia());
        System.out.println(trayectoPrueba2.distanciaMedia());

        Organizacion org = recorridoPrueba.getOrganizacion();
        recorridoPrueba.addTrayecto(trayectoPrueba1, org);

        System.out.println(recorridoPrueba.distanciaTotal());

    }

}
