package utn.frba.huelladecarbono.model.ManejoAmbiental;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class AgenteSectorial {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Transient
    private SectorTerritorial sectorTerritorial;

    public SectorTerritorial getSectorTerritorial() {
        return sectorTerritorial;
    }

    public void setSectorTerritorial(SectorTerritorial sectorTerritorial) {
        this.sectorTerritorial = sectorTerritorial;
    }

    public AgenteSectorial() {
    }

    public AgenteSectorial(Integer id, SectorTerritorial sectorTerritorial) {
        this.id = id;
        this.sectorTerritorial = sectorTerritorial;
    }
}
