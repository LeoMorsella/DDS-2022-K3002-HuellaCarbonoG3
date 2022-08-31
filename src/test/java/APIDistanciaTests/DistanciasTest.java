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

        ArrayList<Trayecto> trayectos = new ArrayList<Trayecto>();
        trayectos.add(trayectoPrueba1);
        trayectos.add(trayectoPrueba2);
        Calendar mesInicio = Calendario.crearFecha(0,2022);
        Calendar mesFin = Calendario.sinFecha();

        Recorrido recorridoPrueba = Recorrido.nuevoRecorrido(new Organizacion(), mesInicio, mesFin);
        System.out.println(trayectoPrueba1.distanciaMedia());
        System.out.println(trayectoPrueba2.distanciaMedia());

        System.out.println(recorridoPrueba.distanciaTotal());

    }

}
