package utn.frba.huelladecarbono.service.CalculoDeHuellaService;


import javax.persistence.*;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

@Getter @Setter
@Entity
@Table(name = "datosActividad")
public class RegistroCalculoHCDatoActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String periodicidad;
    private String periodoImputacion;
    @ManyToOne
    @JoinColumn (name="organizacion_id",referencedColumnName = "id")
    private Organizacion organizacion;
    private Double valor;

    public RegistroCalculoHCDatoActividad(String periodicidad, String periodoImputacion, Organizacion organizacion, Double valor) {
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
        this.organizacion = organizacion;
        this.valor = valor;
    }

    public RegistroCalculoHCDatoActividad() {

    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }



}
