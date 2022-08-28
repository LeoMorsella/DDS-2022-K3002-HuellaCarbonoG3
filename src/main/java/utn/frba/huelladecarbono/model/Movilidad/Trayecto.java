package utn.frba.huelladecarbono.model.Movilidad;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.APIDistanciaService;
import utn.frba.huelladecarbono.model.ManejoAmbiental.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioTrayectos;
import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;

import javax.persistence.*;
import java.util.ArrayList;

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
    // Se agrega carpooling
    @Transient
    private ArrayList<Usuario> pasajeros;
    @Transient
    private Double peso;


    static public Trayecto getTrayecto(Ubicacion salida, Ubicacion llegada, Medio medio, Double peso){
        Trayecto trayectoNuevo = new Trayecto(salida, llegada, medio, peso);
        if(medio == new MedioMotorizado()){
            RepositorioTrayectos.getRepositorio().agregarTrayecto(trayectoNuevo);
        }
        return trayectoNuevo;
    }

    public Trayecto() {
    }
    public Trayecto(Ubicacion salida, Ubicacion llegada, Medio medio, Double peso){
        this.puntoPartida = salida;
        this.puntoLlegada = llegada;
        this.medioTransporte = medio;
        this.peso = peso;
    }

    //TODO Opcion con carpooling
    public Trayecto(Ubicacion salida, Ubicacion llegada, Medio medio, ArrayList<Usuario> pasajeros){
        this.puntoPartida = salida;
        this.puntoLlegada = llegada;
        this.medioTransporte = medio;
        this.pasajeros = pasajeros;
    }

    public Ubicacion getPuntoPartida() {
        return puntoPartida;
    }

    public Double getPeso(){
        return this.peso;
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

    public ArrayList<Usuario> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Usuario> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Double distanciaMedia() throws Exception {
        APIDistanciaService distanciaService = new APIDistanciaService();
        return distanciaService.medirDistancia(puntoPartida, puntoLlegada);
    }


}
