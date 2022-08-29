package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ManejoAmbiental.Miembro;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;

public class CalcularHuellaDeCarbonoMiembro {

    public static Double calcularHCMiembro(Miembro miembro) throws Exception {
        Double HC = 0.0;
        for(Recorrido recorrido : miembro.getRecorridos()) {
            HC += calcularHCRecorrido(recorrido);
        }
        //Se multiplica por 20 para generar el impacto de un mes
        return HC * 20;
    }

    private static Double calcularHCRecorrido (Recorrido recorrido) throws Exception {
        Double HC = 0.0;
        for(Trayecto trayecto : recorrido.getTrayectos()) {
            HC += calcularHCTrayecto(trayecto) * trayecto.getPeso();
        }
        return HC;
    }

    public static Double calcularHCTrayecto(Trayecto trayecto) throws Exception {
        FactoresDeEmision FE = FactoresDeEmision.getInstance();
        System.out.println(FE.getFE("AUTO"));
        return trayecto.distanciaMedia() * FE.getFE(trayecto.getMedioTransporte().getTipo());

    }

    //TODO Version con carpooling
    public  static  Double calcularHCTrayectoCarpooling(Trayecto trayecto) throws Exception {
        FactoresDeEmision FE = FactoresDeEmision.getInstance();
        System.out.println(FE.getFE("AUTO"));
        return (trayecto.distanciaMedia() * FE.getFE(trayecto.getMedioTransporte().getTipo())) / trayecto.getPasajeros().size();
    }

}
