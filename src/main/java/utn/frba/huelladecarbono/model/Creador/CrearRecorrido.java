package utn.frba.huelladecarbono.model.Creador;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.repository.RecorridoRepository;

import java.time.LocalDate;
import java.util.Calendar;

public class CrearRecorrido {
    public static Recorrido crearRecorrido(Organizacion organizacion, Double peso, LocalDate mesInicio, LocalDate mesFin, RecorridoRepository r) {
        Recorrido recorrido = new Recorrido(organizacion,peso,mesInicio,mesFin);
        r.save(recorrido);
        // RepositorioRecorrido.getRepositorio().agregarRecorrido(recorrido);
        return recorrido;
    }
}
