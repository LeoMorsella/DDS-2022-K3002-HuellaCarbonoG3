package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.service.CargaDeMedicionesService.CargaDeMediciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter @Setter
@Entity
public class Area {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    @Transient // Problema ArrayList
    private  ArrayList<Miembro> miembros = new ArrayList<>();
    @ManyToOne
    private  Organizacion organizacion;
    @Transient // Ver como persistir una lista de listas
    private  List<List<DatoDeMedicion>> mediciones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "huellas_carbono")
    @MapKeyColumn(name = "vigencia")
    @Column(name = "valor_huella")
    private HashMap<Vigencia, Double> huellasCarbono = new HashMap<>();

    private Double huellaCarbono = 0.0;

    private Double hcPromedio = 0.0;

    public Area() {
    }

    public Area(Integer id, String nombre, Organizacion organizacion, List<List<DatoDeMedicion>> mediciones) {
        this.id = id;
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.mediciones = mediciones;
    }

    public Area(String nombre, Organizacion organizacion) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        organizacion.setArea(this);
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

    public void agregarHuella(Vigencia vigencia, Double valor){
        this.huellasCarbono.put(vigencia, valor);
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void setHCPromedio(Double valor){
        this.hcPromedio = valor;
    }

    public void setHC(Double valor){
        this.huellaCarbono = valor;
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

    public void addMedicion(ArrayList<DatoDeMedicion> mediciones){
        this.mediciones.add(mediciones);
    }

    public void addMiembro(Miembro miembro){
        miembros.add(miembro);
    }

    public void addMiembro(ArrayList<Miembro> miembros){
        this.miembros.addAll(miembros);
    }

}
