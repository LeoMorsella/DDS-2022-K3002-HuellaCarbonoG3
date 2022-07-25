package utn.frba.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.util.ArrayList;

@Getter @Setter
@Entity
public class TransportePublico extends Medio {
    @Enumerated(EnumType.STRING)
    private TipoTransportePublico tipoTransportePublico;
    private String linea;
    @Transient
    private  ArrayList<Parada> paradas;

    private String ID = "TP";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public TransportePublico() {
    }

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, ArrayList<Parada> paradas, String ID) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.linea = linea;
        this.paradas = paradas;
        this.ID = ID;
    }

    public TransportePublico(TipoTransportePublico tipo, String linea, ArrayList<Parada> list){
        this.tipoTransportePublico = tipo;
        this.linea = linea;
        this.paradas = list;
    }

    public String getTipo() {
        return String.valueOf(tipoTransportePublico);
    }

    public void setTipo(TipoTransportePublico tipoTransportePublico) {
        this.tipoTransportePublico = tipoTransportePublico;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public ArrayList<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(ArrayList<Parada> paradas) {
        this.paradas = paradas;
    }

}
