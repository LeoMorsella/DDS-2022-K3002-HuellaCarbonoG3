package APIDistanciaTests;

import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import BaseDeDatos.BaseDeDatos;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class APIDistanciaTest {
    BaseDeDatos BD = BaseDeDatos.getInstance();

    public APIDistanciaTest() throws Exception {
    }

    @Test
    public void testDistanciaValida() {
        Ubicacion ubicacion1Test = BD.getUbicaciones().get(0);
        Ubicacion ubicacion2Test = BD.getUbicaciones().get(1);
        APIDistanciaService distanciaService = new APIDistanciaService();

        Assertions.assertDoesNotThrow(() -> {
            Double distancia = distanciaService.medirDistancia(ubicacion1Test, ubicacion2Test);
            System.out.println("La distancia es: " + distancia);
        });
    }

}
