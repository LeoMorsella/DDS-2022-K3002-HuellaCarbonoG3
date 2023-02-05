package utn.frba.huelladecarbono.DTO;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;

@Getter
public class ResArea {
    Integer id;
    String nombre;
    Integer organizacionId;

    //crear constructor que reciba un objeto Area
    public ResArea(Area area) {
        this.id = area.getId();
        this.nombre = area.getNombre();
        this.organizacionId = area.getOrganizacion().getId();
    }
}
