package utn.frba.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public abstract class Medio {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String ID;

    abstract public String getID();

    abstract public String getTipo();

    public Medio() {
    }

    public Medio(String ID) {
        this.ID = ID;
    }
}
