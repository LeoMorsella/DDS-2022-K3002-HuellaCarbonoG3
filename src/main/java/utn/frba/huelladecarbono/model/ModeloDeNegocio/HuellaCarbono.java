package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "huellasCarbono")
    @JsonIgnore
    private List<Organizacion> organizaciones = new ArrayList<>();
    public HuellaCarbono() {
    }

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
