package utn.frba.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
@Entity
public class MedioMotorizado extends Medio {

    @Enumerated(EnumType.STRING)
    private TipoVehiculoMotorizado tipoVehiculoMotorizado;
    @Enumerated(EnumType.STRING)
    private TipoCombustible tipoCombustible;
    private String patente;
    private String ID = "MM";
    private Boolean esServicioContratado;
    private String tipoServicio;

    public String getID() {
        return ID;
    }

    public String getTipo() {
        return String.valueOf(tipoVehiculoMotorizado);
    }

    public MedioMotorizado() {
    }

    public void setTipo(TipoVehiculoMotorizado tipoVehiculoMotorizado) {
        this.tipoVehiculoMotorizado = tipoVehiculoMotorizado;
    }

    public MedioMotorizado(TipoVehiculoMotorizado tipoVehiculoMotorizado, TipoCombustible tipoCombustible, String patente, String ID, Boolean esServicioContratado, String tipoServicio) {
        this.tipoVehiculoMotorizado = tipoVehiculoMotorizado;
        this.tipoCombustible = tipoCombustible;
        this.patente = patente;
        this.ID = ID;
        this.esServicioContratado = esServicioContratado;
        this.tipoServicio = tipoServicio;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Boolean getEsServicioContratado() {
        return esServicioContratado;
    }

    public void setEsServicioContratado(Boolean esServicioContratado) {
        this.esServicioContratado = esServicioContratado;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Double getFE() {
        return null; // TODO
    }
}
