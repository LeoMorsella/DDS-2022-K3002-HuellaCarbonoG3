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
        Double valor = CalculadoraHCOrganizacion.calcularHC(organizacion, mesInicio, mesFin);
        organizacion.setHC(valor);
        Vigencia vigencia = new Vigencia(mesInicio, mesFin);
        organizacion.agregarHuella(vigencia, valor);
        return valor;
    }

    public Double calcularHCArea(Area area, Calendar mesInicio, Calendar mesFin) {
        Double valor = CalculadoraHCArea.calcularHC(area, mesInicio, mesFin);
        area.setHC(valor);
        Vigencia vigencia = new Vigencia(mesInicio, mesFin);
        area.agregarHuella(vigencia, valor);
        return valor;
    }

    public Double calcularHCMiembro(Miembro miembro, Calendar mesInicio, Calendar mesFin, Organizacion organizacion) throws Exception {
        Double valor = CalculadoraHCMiembro.calcularHC(miembro, mesInicio, mesFin, organizacion);
        Vigencia vigencia = new Vigencia(mesInicio, mesFin);
        miembro.agregarHuella(vigencia, valor);
        return valor;
    }

    public Double calcularHCMedicion(List<DatoDeMedicion> datoDeMedicion, Calendar mesInicio, Calendar mesFin) {
        Double valor = CalculadoraHCMedicion.calcularHC(datoDeMedicion, k, mesInicio, mesFin);
        Vigencia vigencia = new Vigencia(mesInicio, mesFin);
        for(DatoDeMedicion dato : datoDeMedicion){
            dato.agregarHuella(vigencia, valor);
        }
        return valor;
    }

    public Double calcularHCSectorTerritorial(SectorTerritorial sectorTerritorial, Calendar mesInicio, Calendar mesFin) {
        Double HCTotal = 0.0;
        for (Organizacion organizacion : sectorTerritorial.getOrganizaciones()) {
            HCTotal += CalculadoraHCOrganizacion.calcularHC(organizacion, mesInicio, mesFin);
        }
        sectorTerritorial.setHC(HCTotal);
        Vigencia vigencia = new Vigencia(mesInicio, mesFin);
        sectorTerritorial.agregarHuella(vigencia, HCTotal);
        return HCTotal;
    }

    public Double calcularHCMensual(Miembro miembro, Calendar mes, Organizacion organizacion) throws Exception {
        //TODO
        return CalculadoraHCMiembro.calcularHC(miembro, mes, mes, organizacion);
    }

    public Double calcularImpactoIndividual(Miembro miembro, Organizacion organizacion, Calendar mesInicio, Calendar mesFin) throws Exception {
        Double valor = CalculadoraHCMiembro.calcularImpactoIndividual(miembro, organizacion, mesInicio, mesFin);
        miembro.setImpacto(valor);
        return valor;
    }

    public Double calcularHCPromedio(Organizacion organizacion,Calendar mesInicio,Calendar mesFin) throws Exception {
        Double valor = CalculadoraHCOrganizacion.HCpromedio(organizacion, mesInicio, mesFin);
        organizacion.setHCPromedio(valor);
        return valor;
    }

    public Double calcularHCPromedio(Area area,Calendar mesInicio,Calendar mesFin) throws Exception {
        Double valor = CalculadoraHCArea.HCpromedio(area, mesInicio, mesFin);
        area.setHCPromedio(valor);
        return valor;
    }
}
