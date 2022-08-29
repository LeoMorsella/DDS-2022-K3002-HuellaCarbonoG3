package utn.frba.huelladecarbono.model.CalculoDeDistancias.Strategies;

import utn.frba.huelladecarbono.model.ManejoAmbiental.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

import java.util.ArrayList;

public class StrategyTransportePublico extends StrategyTransporte{

    public Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio transportePublico) throws Exception {
        ArrayList<Parada> paradas = ((TransportePublico) transportePublico).getParadas();
        Double distanciaTotal = 0.0;
        Parada paradaTemp = paradas.get(0);
        for(int i = paradas.indexOf(ubicacion1); i < paradas.indexOf(ubicacion2); i++){
            paradaTemp = paradas.get(i);
            distanciaTotal += paradaTemp.distancaAProximaParada();
        }
        return distanciaTotal;
    }
}
