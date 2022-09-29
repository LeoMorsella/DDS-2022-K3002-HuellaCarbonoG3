package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.time.LocalDate;

public class CalculadoraHCOrganizacion {
    public static Double calcularHC(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin){
        Double HC = 0.0;

        for( Area area : organizacion.getAreas()) {
            HC += CalculadoraHCService.getCalculadoraHC().calcularHCArea(area, mesInicio, mesFin);
        }
        return HC;
    }

    public static Double HCpromedio(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) {
        return calcularHC(organizacion, mesInicio, mesFin) / organizacion.getMiembros().size();
    }
}
