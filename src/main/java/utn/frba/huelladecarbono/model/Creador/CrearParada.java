package utn.frba.huelladecarbono.model.Creador;

import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.repository.ParadaRepository;

public class CrearParada {
    public static Parada crearParada(String nombre, Ubicacion ubicacion, ParadaRepository p) {
        Parada parada = new Parada(nombre,ubicacion);
        p.save(parada);
        return parada;
    }
}
