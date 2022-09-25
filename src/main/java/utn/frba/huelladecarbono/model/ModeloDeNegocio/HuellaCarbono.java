package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import java.util.Calendar;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class HuellaCarbono {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Calendar fechaIni;

    @Basic
    @Temporal(TemporalType.DATE)
    private Calendar fechaFin;
    
    private Double huella;

    public HuellaCarbono(Calendar ini, Calendar fin, Double valor){
        this.fechaIni = ini;
        this.fechaFin = fin;
        this.huella = valor;
    }

    public void setInicio(Calendar fecha){
        this.fechaIni = fecha;
    }

    public void setFin(Calendar fecha){
        this.fechaFin = fecha;
    }

    public void setHuella(Double valor){
        this.huella = valor;
    }

    public Double getHuella(){
        return this.huella;
    }

    public Calendar getFechaIni(){
        return this.fechaIni;
    }

    public Calendar getFechaFin(){
        return this.fechaFin;
    }
}
