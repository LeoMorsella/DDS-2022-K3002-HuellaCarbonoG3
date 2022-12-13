package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;

import java.time.LocalDate;

@Getter
public class ResRecorrido {
    Integer id;
    ResOrganizacion organizacion;
    String nombre;
    Double peso;
    String fechaI;
    String fechaF;
    Boolean estaActivo;
    Integer cantTrayectos;

    public ResRecorrido(Recorrido recorrido) {
        this.id = recorrido.getId();
        this.organizacion = new ResOrganizacion(recorrido.getOrganizacion());
        this.nombre = recorrido.getNombre();
        this.peso = recorrido.getPeso();
        this.fechaI = recorrido.getFechaInicio();
        this.fechaF = recorrido.getFechaFin();
        this.estaActivo = recorrido.getEstaActivo();
        this.cantTrayectos = recorrido.getTrayectos().size();
    }
}
