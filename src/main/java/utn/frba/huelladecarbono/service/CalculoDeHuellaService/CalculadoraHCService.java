package utn.frba.huelladecarbono.service.CalculoDeHuellaService;


import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;

import java.util.Calendar;
import java.util.List;

public class CalculadoraHCService {
    Double k = 0.0;
    private static CalculadoraHCService instance = null;

    private CalculadoraHCService() {
    }

    public static CalculadoraHCService getCalculadoraHC() {
        if (instance == null) {
            instance = new CalculadoraHCService();
        }
        return instance;
    }

    public Double calcularHCOrganizacion(Organizacion organizacion, Calendar mesInicio, Calendar mesFin) {
        return CalculadoraHCOrganizacion.calcularHC(organizacion, mesInicio, mesFin);
    }

    public Double calcularHCArea(Area area, Calendar mesInicio, Calendar mesFin) {
        return CalculadoraHCArea.calcularHC(area, mesInicio, mesFin);
    }

    public Double calcularHCMiembro(Miembro miembro, Calendar mesInicio, Calendar mesFin) throws Exception {
        return CalculadoraHCMiembro.calcularHC(miembro, mesInicio, mesFin);
    }

    public Double calcularHCMedicion(List<DatoDeMedicion> datoDeMedicion, Calendar mesInicio, Calendar mesFin) {
        return CalculadoraHCMedicion.calcularHC(datoDeMedicion, k, mesInicio, mesFin);
    }

    public Double calcularHCSectorTerritorial(SectorTerritorial sectorTerritorial, Calendar mesInicio, Calendar mesFin) {
        Double HCTotal = 0.0;
        for (Organizacion organizacion : sectorTerritorial.getOrganizaciones()) {
            HCTotal += CalculadoraHCOrganizacion.calcularHC(organizacion, mesInicio, mesFin);
        }
        return HCTotal;
    }

    public Double calcularHCMensual(Miembro miembro, Calendar mes) throws Exception {
        //TODO
        return CalculadoraHCMiembro.calcularHC(miembro, mes, mes);
    }

    public Double calcularImpactoIndividual(Miembro miembro, Organizacion organizacion, Calendar mesInicio, Calendar mesFin) throws Exception {
        return CalculadoraHCMiembro.calcularImpactoIndividual(miembro, organizacion, mesInicio, mesFin);
    }
}
