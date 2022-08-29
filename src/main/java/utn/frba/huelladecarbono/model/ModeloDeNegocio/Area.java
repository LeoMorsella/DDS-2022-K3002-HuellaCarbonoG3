package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalcularHuellaDeCarbonoService;
import utn.frba.huelladecarbono.service.CargaDeMedicionesService.CargaDeMediciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Area {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    @Transient
    private  ArrayList<Miembro> miembros;

    @Transient
    private  Organizacion organizacion;
    @Transient
    private  List<List<DatoDeMedicion>> mediciones = null;

    public Area() {
    }

    public Area(Integer id, String nombre, ArrayList<Miembro> miembros, Organizacion organizacion, List<List<DatoDeMedicion>> mediciones) {
        this.id = id;
        this.nombre = nombre;
        this.miembros = miembros;
        this.organizacion = organizacion;
        this.mediciones = mediciones;
    }

    public Area(String nombre, ArrayList<Miembro> miembros, Organizacion organizacion) {
        this.nombre = nombre;
        this.miembros = miembros;
        this.organizacion = organizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(ArrayList<Miembro> miembro) {
        this.miembros = miembro;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<List<DatoDeMedicion>> getMediciones() {
        return mediciones;
    }

    public void cargarMediciones(String DireccionExcel) {
        String filePath = DireccionExcel;
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> nuevasDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        mediciones.add(nuevasDatoDeMedicion);
    }

    public Double calcularHC() throws Exception {
        return CalcularHuellaDeCarbonoService.getCalculadora().calcularHC(this);
    }

    public Double HCpromedio() throws Exception {
        return CalcularHuellaDeCarbonoService.getCalculadora().HCpromedio(this);
    }
}
