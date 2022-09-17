package utn.frba.huelladecarbono.model.Movilidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Recorrido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Transient // Problema ArrayList
    public ArrayList<Trayecto> trayectos = new ArrayList<>();
    @Transient // OneToMany?
    private Organizacion organizacion;
    private Double peso;
    private Calendar mesInicio;
    private Calendar mesFin;

    public Recorrido(Organizacion organizacion, Double peso, Calendar mesInicio, Calendar mesFin) {
        this.organizacion = organizacion;
        this.mesInicio = mesInicio;
        this.mesFin = mesFin;
        this.peso = peso;
        //RepositorioRecorrido.getRepositorio().agregarRecorrido(nuevoRecorrido);
    }

    public void addTrayecto(Trayecto trayecto){
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
}

