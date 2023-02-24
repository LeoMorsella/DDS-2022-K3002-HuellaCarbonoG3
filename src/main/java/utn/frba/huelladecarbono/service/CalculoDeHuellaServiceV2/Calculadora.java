package utn.frba.huelladecarbono.service.CalculoDeHuellaServiceV2;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.FactoresDeEmision;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Registro;
import java.time.LocalDate;
import java.util.List;

public class Calculadora {

    public static Double calcularHCOrganizacion(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
       Double hc = 0.0;
        // CALCULAR HC MES ACTUAL
        if (mesFin.isEqual(LocalDate.now())) {
           List<Area> areas = organizacion.getAreas();
           for (Area area : areas) {
               hc += area.getHCMediciones();
               for (Miembro miembro : area.getMiembros()) {
                   for (Recorrido recorrido : miembro.getRecorridos()) {
                       Double hcR = 0.0;
                       for (Trayecto trayecto : recorrido.getTrayectos()) {
                           FactoresDeEmision FE = FactoresDeEmision.getInstance();
                           Double distanciaMedia = trayecto.distanciaMedia();
                           Double fe = FE.getFE(trayecto.getMedioTransporte().getTipo());
                           Double cantPasajeros = (double) (trayecto.getPasajeros().size());
                           hcR += distanciaMedia * fe / cantPasajeros ;
                       }
                       hc += hcR;
                   }
               }
           }
       }
        // CALCULO HC ANTERIOR
        if (mesInicio.isBefore(LocalDate.now())) {
            List<Registro> registros = organizacion.getHcMensual();
            List<Registro> select = registros.stream().filter(registro -> registro.getMes().isAfter(mesInicio) && registro.getMes().isBefore(LocalDate.now())).toList();

            for (Registro registro : select) {
                hc += registro.getHc();
            }
        }

        return hc;
    }
}
