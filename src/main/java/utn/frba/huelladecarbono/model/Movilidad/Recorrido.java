package utn.frba.huelladecarbono.model.Movilidad;

import lombok.Getter;
import lombok.Setter;
import sun.util.resources.cldr.ne.CalendarData_ne_IN;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Calendar mesInicio;
    private Calendar mesFin;

    public ArrayList<Trayecto> getTrayectos() {
        return trayectos;
    }

    public Recorrido(Organizacion organizacion, Calendar mesInicio, Calendar mesFin) {
        this.organizacion = organizacion;
        this.mesInicio = mesInicio;
        this.mesFin = mesFin;
    }
    public static Recorrido nuevoRecorrido(Organizacion organizacion, Calendar mesInicio, Calendar mesFin){
        Recorrido nuevoRecorrido = new Recorrido(organizacion, mesInicio, mesFin);
        //RepositorioRecorrido.getRepositorio().agregarRecorrido(nuevoRecorrido);
        return nuevoRecorrido;
    }

    //Si se cambian los valores hay que remover el trayecto y agregarlo de nuevo
    public void addTrayecto(Trayecto trayecto, Double factorDeUso, Organizacion organizacion){
        trayecto.setFactorDeUso(factorDeUso);
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

