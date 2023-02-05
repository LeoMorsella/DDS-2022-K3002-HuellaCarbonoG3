package utn.frba.huelladecarbono.DTO;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;

import java.time.LocalDate;

@Getter
public class ResDatoDeActividad {
    private ResArea area;
    private LocalDate fecha;

    public ResDatoDeActividad(Area area, LocalDate fecha) {
        this.area = new ResArea(area);
        this.fecha = fecha;
    }
}
