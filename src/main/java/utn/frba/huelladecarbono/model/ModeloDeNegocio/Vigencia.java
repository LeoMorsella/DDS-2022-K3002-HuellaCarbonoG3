package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import java.util.Calendar;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Vigencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Calendar fechaInicio;
    private Calendar fechaFin;

    public Vigencia(Calendar fechaIni, Calendar fechaFin){
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaIni;
    }

    public Calendar getFechaInicio(){
        return this.fechaInicio;
    }

    public Calendar getFechaFin(){
        return this.fechaFin;
    }

    public void setInicio(Calendar fecha){
        this.fechaInicio = fecha;
    }

    public void setFin(Calendar fecha){
        this.fechaFin = fecha;
    }
}
