package APIDistanciaTests;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.CargaDatos;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import org.junit.Test;

import java.util.ArrayList;

public class DistanciasTest {

    @Test
    public void testDevuelveDistancia() throws Exception {
        Trayecto trayectoPrueba1 = CargaDatos.cargarTrayecto1();
        Trayecto trayectoPrueba2 = CargaDatos.cargarTrayecto3();

        ArrayList<Trayecto> trayectos = new ArrayList<Trayecto>();
        trayectos.add(trayectoPrueba1);
        trayectos.add(trayectoPrueba2);

        Recorrido recorridoPrueba = Recorrido.nuevoRecorrido(new Organizacion());
        System.out.println(trayectoPrueba1.distanciaMedia());
        System.out.println(trayectoPrueba2.distanciaMedia());

        System.out.println(recorridoPrueba.distanciaTotal());

    }

}
