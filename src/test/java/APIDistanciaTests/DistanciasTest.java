package APIDistanciaTests;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import org.junit.Test;

import java.util.ArrayList;

public class DistanciasTest {

    @Test
    public void testDevuelveDistancia() throws Exception {
        Ubicacion ubicacion1Inic = new Ubicacion("ARGENTINA", "MISIONES",
                "MONTECARLO", "CARAGUATAY ", "maipu", "100");
        Ubicacion ubicacion1Final = new Ubicacion("ARGENTINA", "MISIONES",
                "MONTECARLO", "CARAGUATAY ", "maipu", "200");
        Ubicacion ubicacion2Inic = new Ubicacion("ARGENTINA", "MISIONES",
                "MONTECARLO", "CARAGUATAY ", "maipu", "300");
        Ubicacion ubicacion2Final = new Ubicacion("ARGENTINA", "MISIONES",
                "MONTECARLO", "CARAGUATAY ", "maipu", "400");
        Trayecto trayectoPrueba1 = Trayecto.getTrayecto(ubicacion1Inic, ubicacion1Final, new MedioMotorizado());
        Trayecto trayectoPrueba2 = Trayecto.getTrayecto(ubicacion2Inic, ubicacion2Final, new MedioNoMotorizado());

        ArrayList<Trayecto> trayectos = new ArrayList<Trayecto>();
        trayectos.add(trayectoPrueba1);
        trayectos.add(trayectoPrueba2);

        Recorrido recorridoPrueba = Recorrido.nuevoRecorrido(new Organizacion());
        System.out.println(trayectoPrueba1.distanciaMedia());
        System.out.println(trayectoPrueba2.distanciaMedia());

        System.out.println(recorridoPrueba.distanciaTotal());

    }

}
