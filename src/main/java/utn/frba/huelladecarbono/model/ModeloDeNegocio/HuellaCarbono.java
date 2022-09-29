package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import java.time.LocalDate;
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


    @Column(columnDefinition = "DATE")
    private LocalDate fechaIni;

    @Transient
    private LocalDate fechaFin;

    @Column
    private Double huella;


    public HuellaCarbono(LocalDate fechaIni, LocalDate fechaFin, Double huella) {
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.huella = huella;
    }

    public HuellaCarbono() {

    }

    public void setInicio(LocalDate fecha){
        this.fechaIni = fecha;
    }

    public void setFin(LocalDate fecha){
        this.fechaFin = fecha;
    }

    public void setHuella(Double valor){
        this.huella = valor;
    }

    public Double getHuella(){
        return this.huella;
    }

    public LocalDate getFechaIni(){
        return this.fechaIni;
    }

    public LocalDate getFechaFin(){
        return this.fechaFin;
    }
}
