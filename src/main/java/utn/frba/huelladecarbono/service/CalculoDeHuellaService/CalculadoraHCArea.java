package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.ListaDeDatosDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CalculadoraHCArea {
    public static Double calcularHC(Area area, LocalDate mesInicio, LocalDate mesFin){
        Double hc = 0.0;
        hc += area.getHCMediciones();
        for (Miembro miembro : area.getMiembros()) {
            for (Recorrido recorrido : miembro.getRecorridos()) {
                Random random = new Random();
                hc += (random.nextDouble() + 1.0) * recorrido.getCantidadDeTrayectos();
            }
        }
        return hc;
    }

    public static Double HCpromedio(Area area, LocalDate mesInicio, LocalDate mesFin) {
        return calcularHC(area, mesInicio, mesFin) / area.getMiembros().size();
    }
}
