package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Getter @Setter
@Entity
public class SectorTerritorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String municipio;
    private String provincia;
    private Double huellaCarbono = 0.0;

    @ElementCollection
    @CollectionTable(name = "huellas_carbono")
    @MapKeyColumn(name = "vigencia")
    @Column(name = "valor_huella")
    private HashMap<Vigencia, Double> huellasCarbono = new HashMap<>();
    
    @OneToOne
    private AgenteSectorial agenteSectorial;

    public SectorTerritorial(AgenteSectorial agenteSectorial) {
        this.agenteSectorial = agenteSectorial;
    }

    private ArrayList<Organizacion> generarOrganizacionesPorCriterio(List<Organizacion> organizacionesTotales, Object criterioUbicacion) {
        ArrayList<Organizacion> organizacionesDelSector = new ArrayList<>();
        for (Organizacion organizacion : organizacionesTotales) {
            if (organizacion.getUbicacion().getMunicipio() == criterioUbicacion)
                organizacionesTotales.add(organizacion);
        }
        return organizacionesDelSector;
    }

    public ArrayList<Organizacion> getOrganizaciones() {
        List<Organizacion> organizacionesTotales = RepositorioOrganizaciones.getRepositorio().getOrganizaciones();
        if (municipio != null){
            return generarOrganizacionesPorCriterio(organizacionesTotales, municipio);
        }
        else {
            return generarOrganizacionesPorCriterio(organizacionesTotales, provincia);
        }
    }

    public String getProvincia() {
        return provincia;
    }

    public void setHC(Double valor){
        this.huellaCarbono = valor;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public AgenteSectorial getAgenteSectorial() {
        return agenteSectorial;
    }

    public void agregarHuella(Vigencia vigencia, Double valor){
        this.huellasCarbono.put(vigencia, valor);
    }

    public void setAgenteSectorial(AgenteSectorial agenteSectorial) {
        this.agenteSectorial = agenteSectorial;
    }

    public SectorTerritorial() {
    }

    public SectorTerritorial(Integer id, String municipio, String provincia, AgenteSectorial agenteSectorial) {
        this.id = id;
        this.municipio = municipio;
        this.provincia = provincia;
        this.agenteSectorial = agenteSectorial;
    }


}
