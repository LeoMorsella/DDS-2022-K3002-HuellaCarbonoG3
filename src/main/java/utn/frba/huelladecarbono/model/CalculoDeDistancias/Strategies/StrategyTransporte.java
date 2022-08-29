package utn.frba.huelladecarbono.model.CalculoDeDistancias.Strategies;

import utn.frba.huelladecarbono.model.ManejoAmbiental.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;

public abstract class  StrategyTransporte {

    public abstract Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio medio) throws Exception;

}
