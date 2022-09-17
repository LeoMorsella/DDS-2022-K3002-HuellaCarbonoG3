package utn.frba.huelladecarbono.model.CalculoDeDistancias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Distancia {
    @Id
    @GeneratedValue
    private int id;
    private Double valor;
    private String unidad;

    public Double getValor() {return valor;}
    public String getUnidad() {return unidad;}

}
