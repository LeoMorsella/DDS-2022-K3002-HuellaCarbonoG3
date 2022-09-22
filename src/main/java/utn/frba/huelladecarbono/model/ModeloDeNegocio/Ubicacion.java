package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idPais;
    private String pais;
    private Integer idProvincia;
    private String provincia;
    private Integer idMunicipio;
    private String municipio;
    private Integer idLocalidad;
    private String localidad;
    private String calle;
    private String altura;

    public Ubicacion(String pais, String provincia, String municipio, String localidad, String calle, String altura) throws Exception {
        APIDistanciaService API = new APIDistanciaService();
        this.pais = pais;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
        this.idPais = API.buscarId("pais", -1, this);
        this.idProvincia = API.buscarId("provincia", idPais, this);
        this.idMunicipio = API.buscarId("municipio", idProvincia, this);
        this.idLocalidad = API.buscarId("localidad", idMunicipio, this);
    }

    public Ubicacion(Integer id, String provincia, String municipio, String localidad, String calle, String altura) throws Exception {
        APIDistanciaService API = new APIDistanciaService();
        this.id = id;
        this.pais = pais;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
        this.idPais = API.buscarId("pais", -1, this);
        this.idProvincia = API.buscarId("provincia", idPais, this);
        this.idMunicipio = API.buscarId("municipio", idProvincia, this);
        this.idLocalidad = API.buscarId("localidad", idMunicipio, this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ubicacion(Integer idPais, String pais, Integer idProvincia, String provincia, Integer idMunicipio, String municipio, Integer idLocalidad, String localidad, String calle, String altura) {
        this.idPais = idPais;
        this.pais = pais;
        this.idProvincia = idProvincia;
        this.provincia = provincia;
        this.idMunicipio = idMunicipio;
        this.municipio = municipio;
        this.idLocalidad = idLocalidad;
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
    }
}
