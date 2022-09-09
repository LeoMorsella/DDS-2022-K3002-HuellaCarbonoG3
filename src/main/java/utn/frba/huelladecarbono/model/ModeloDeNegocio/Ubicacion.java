package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int idPais;
    private String pais;
    private int idProvincia;
    private String provincia;
    private int idMunicipio;
    private String municipio;
    private int idLocalidad;
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
}
