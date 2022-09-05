package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.Calendar;

public class CalculadoraHCOrganizacion {
    public static Double calcularHC(Organizacion organizacion, Calendar mesInicio, Calendar mesFin){
        Double HC = 0.0;

        for( Area area : organizacion.getAreas()) {
            HC += CalculadoraHCService.getCalculadoraHC().calcularHCArea(area, mesInicio, mesFin);
        }
        return HC;
    }

    public static Double HCpromedio(Organizacion organizacion, Calendar mesInicio, Calendar mesFin) {
        return calcularHC(organizacion, mesInicio, mesFin) / organizacion.getMiembros().size();
    }
}
