package utn.frba.huelladecarbono.model.MedioDeTransporte;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.Distancia;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter @Setter
@Entity
public class Parada {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String ID;
    private String nombre;
    @Transient
    private Ubicacion ubicacion;
    @Transient
    private Distancia distanciaAProximaParada;

    public Parada() {
    }

    public Parada(String nom, Ubicacion ubi){
        this.nombre = nom;
        this.ubicacion = ubi;
    }

    public Parada(String nombre, Ubicacion ubicacion, Distancia distanciaAProximaParada) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.distanciaAProximaParada = distanciaAProximaParada;
    }

    public Double distancaAProximaParada() {
        if (distanciaAProximaParada.getValor()!= 0) {
            return distanciaAProximaParada.getValor();
        }
        else{
            throw new RuntimeException("No hay siguiente parada (Parada Terminal)");
        }
    }
}
