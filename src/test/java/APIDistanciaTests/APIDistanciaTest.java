package APIDistanciaTests;

import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import CargaDatos.CargarDatos;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class APIDistanciaTest {

    @Test
    public void testDistanciaValida() throws Exception {

        Ubicacion ubicacion1Test = CargarDatos.cargarUbicacion1();
        Ubicacion ubicacion2Test = CargarDatos.cargarUbicacion2();

        APIDistanciaService distanciaService = new APIDistanciaService();
        Assertions.assertDoesNotThrow(() -> distanciaService.medirDistancia(ubicacion1Test, ubicacion2Test));
    }

}
