package APIDistanciaTests;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.APITokenGenerator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class APITokenTest {

    @Test
    public void testCrearTokenValido() throws Exception {
        APITokenGenerator tokenGenerator = new APITokenGenerator();
        Assertions.assertDoesNotThrow(() -> tokenGenerator.obtenerToken("EmailPrueba12244@hotmail.com"));
    }
}
