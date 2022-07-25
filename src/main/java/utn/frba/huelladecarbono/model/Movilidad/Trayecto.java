package utn.frba.huelladecarbono.model.Movilidad;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.APIDistanciaService;
import utn.frba.huelladecarbono.model.ManejoAmbiental.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioTrayectos;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Trayecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Transient
    private Ubicacion puntoPartida;
    @Transient
    private Ubicacion puntoLlegada;
    @Transient
    private Medio medioTransporte;


    static public Trayecto getTrayecto(Ubicacion salida, Ubicacion llegada, Medio medio){
        Trayecto trayectoNuevo = new Trayecto(salida, llegada, medio);
        if(medio == new MedioMotorizado()){
            RepositorioTrayectos.getRepositorio().agregarTrayecto(trayectoNuevo);
        }
        return trayectoNuevo;
    }
    public Trayecto() {
    }
    public Trayecto(Ubicacion salida, Ubicacion llegada, Medio medio){
        this.puntoPartida = salida;
        this.puntoLlegada = llegada;
        this.medioTransporte = medio;
    }

    public Ubicacion getPuntoPartida() {
        return puntoPartida;
    }

    public void setPuntoPartida(Ubicacion puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public Ubicacion getPuntoLlegada() {
        return puntoLlegada;
    }

    public void setPuntoLlegada(Ubicacion puntoLlegada) {
        this.puntoLlegada = puntoLlegada;
    }

    public Medio getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(Medio medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public Double distanciaMedia() throws Exception {
        APIDistanciaService distanciaService = new APIDistanciaService();
        return distanciaService.medirDistancia(puntoPartida, puntoLlegada);
    }


}
