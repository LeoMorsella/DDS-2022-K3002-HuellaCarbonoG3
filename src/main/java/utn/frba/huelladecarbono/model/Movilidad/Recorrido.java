package utn.frba.huelladecarbono.model.Movilidad;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Getter @Setter
@Entity
public class Recorrido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Transient
    private ArrayList<Trayecto> trayectos;
    @Transient
    private Organizacion organizacion;

    public ArrayList<Trayecto> getTrayectos() {
        return trayectos;
    }

    public Recorrido(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
    public static Recorrido nuevoRecorrido(Organizacion organizacion){
        Recorrido nuevoRecorrido = new Recorrido(organizacion);
        //RepositorioRecorrido.getRepositorio().agregarRecorrido(nuevoRecorrido);
        return nuevoRecorrido;
    }

    //Si se cambian los valores hay que remover el trayecto y agregarlo de nuevo
    public void addTrayecto(Trayecto trayecto, Date fechaDeInicio, Date fechaDeFin, Double factorDeUso, Organizacion organizacion){
        trayecto.setFactorDeUso(factorDeUso);
        trayecto.setFechaDeFin(fechaDeFin);
        trayecto.setFechaDeInicio(fechaDeInicio);
        this.trayectos.add(trayecto);
    }

    public void removeTrayecto(Trayecto trayecto){
        trayectos.remove(trayecto);
    }

    public Double distanciaTotal() throws Exception {
        Double distanciaTotal = Double.parseDouble("0");
        for (Trayecto trayecto : trayectos){
            distanciaTotal = distanciaTotal + trayecto.distanciaMedia();
        }
        return distanciaTotal;
    }

    public Recorrido() {
    }
}

