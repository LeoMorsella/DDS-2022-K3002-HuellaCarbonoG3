package utn.frba.huelladecarbono.model.Movilidad;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioTrayectos;
import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transient
    private Double factorDeUso;
    @Transient
    private Date fechaDeInicio;
    @Transient
    private Date fechaDeFin;

    public Trayecto() {
    }

    static public Trayecto getTrayecto(Ubicacion salida, Ubicacion llegada, Medio medio){
        Trayecto trayectoNuevo = new Trayecto(salida, llegada, medio);
        if(medio == new MedioMotorizado() && !(RepositorioTrayectos.getRepositorio().getTrayectos().contains(trayectoNuevo))){
            RepositorioTrayectos.getRepositorio().agregarTrayecto(trayectoNuevo);
        }
        return trayectoNuevo;
    }

    public Trayecto(Ubicacion salida, Ubicacion llegada, Medio medio){
        this.puntoPartida = salida;
        this.puntoLlegada = llegada;
        this.medioTransporte = medio;
    }

    public Ubicacion getPuntoPartida() {
        return puntoPartida;
    }

    public Double getPeso(){
        return this.factorDeUso;
    }

    public Double getFactorDeUso() {
        return factorDeUso;
    }

    public void setFactorDeUso(Double factorDeUso) {
        this.factorDeUso = factorDeUso;
    }

    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public Date getFechaDeFin() {
        return fechaDeFin;
    }

    public void setFechaDeFin(Date fechaDeFin) {
        this.fechaDeFin = fechaDeFin;
    }

    public List<Miembro> getPasajeros(){
        return RepositorioMiembros.getRepositorio().getMiembros().stream()
                .filter(miembro -> miembro.getRecorridos().stream().
                        anyMatch(recorrido -> recorrido.getTrayectos().stream().
                                anyMatch(trayecto -> trayecto.equals(this))))
                .collect(Collectors.toList());
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
        CalculoDistanciaService distanciaService = new CalculoDistanciaService();
        return distanciaService.calcularDistancia(puntoPartida, puntoLlegada, medioTransporte);
    }


}
