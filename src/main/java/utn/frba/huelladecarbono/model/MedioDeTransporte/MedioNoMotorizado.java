package utn.frba.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
@Entity
public class MedioNoMotorizado extends Medio {
    @Enumerated(EnumType.STRING)
    private TipoMedioNoMotorizado tipo;

    public String getTipo() {
        return String.valueOf(tipo);
    }

    public void setTipo(TipoMedioNoMotorizado tipo) {
        this.tipo = tipo;
    }

    private String ID = "MNM";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getFE() {
        return 0.0;
    }

    public MedioNoMotorizado(TipoMedioNoMotorizado tipo, String ID) {
        this.tipo = tipo;
        this.ID = ID;
    }

    public MedioNoMotorizado() {
    }
}
