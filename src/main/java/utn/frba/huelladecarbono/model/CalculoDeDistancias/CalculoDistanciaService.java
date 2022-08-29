package utn.frba.huelladecarbono.model.CalculoDeDistancias;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.Strategies.StrategyTransporte;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Strategies.StrategyTransportePublico;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Strategies.StrategyVehiculoMotorizado;
import utn.frba.huelladecarbono.model.ManejoAmbiental.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

public class CalculoDistanciaService {

    StrategyTransporte strategy = null;


    //Se podría extender para que reciba un trayecto directamente
    public Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio medio) throws Exception {
        if(medio.getClass() == TransportePublico.class) this.strategy = new StrategyTransportePublico();
        if(medio.getClass() == MedioMotorizado.class) this.strategy = new StrategyVehiculoMotorizado();
        return strategy.calcularDistancia(ubicacion1, ubicacion2, medio);
    }

}
