package utn.frba.huelladecarbono.model.CalculoDeDistancias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double valor;
    private String unidad;

    public Double getValor() {return valor;}
    public String getUnidad() {return unidad;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
