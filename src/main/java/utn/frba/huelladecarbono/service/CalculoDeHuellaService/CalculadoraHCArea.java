package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

import java.util.Calendar;
import java.util.List;

public class CalculadoraHCArea {
    public static Double calcularHC(Area area, Calendar mesInicio, Calendar mesFin){
        Double HC = 0.0;
        for (Miembro miembro : area.getMiembros()) {
            try {
                HC += CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (List<DatoDeMedicion> mediciones :area.getMediciones() ) {
            HC += CalculadoraHCService.getCalculadoraHC().calcularHCMedicion(mediciones, mesInicio, mesFin);
        }
        return HC;
    }

    public Double HCpromedio(Area area, Calendar mesInicio, Calendar mesFin) {
        return this.calcularHC(area, mesInicio, mesFin) / area.getMiembros().size();
    }
}
