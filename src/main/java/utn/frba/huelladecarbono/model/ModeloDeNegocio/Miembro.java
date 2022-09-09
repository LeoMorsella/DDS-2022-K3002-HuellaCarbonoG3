package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Getter @Setter
@Entity
@Table(name = "miembro")
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private int numDoc;
    @Transient
    private ArrayList<Area> areas;
    @Transient
    private ArrayList<Recorrido> recorridos;

    private String mail;

    private String telefono;

    public Miembro(String nom, String ape, String tipoDocu, int numeroDoc, ArrayList<Area> listaAreas,
                   ArrayList<Recorrido> recorridos) {
        this.nombre = nom;
        this.apellido = ape;
        this.tipoDoc = tipoDocu;
        this.numDoc = numeroDoc;
        this.areas = listaAreas;

        areas.forEach(area -> area.addMiembro(this));

        AtomicReference<Double> pesoTotal = new AtomicReference<>(Double.valueOf(0));
        recorridos.forEach( recorrido -> pesoTotal.updateAndGet(v -> v + recorrido.getPeso()));
        if(pesoTotal.get() != 1) {
            throw new RuntimeException("Peso inválido en los recorrridos");
        }
        this.recorridos = recorridos;
        RepositorioMiembros.getRepositorio().agregarMiembro(this);
    }

    public Miembro(Integer id, String nombre, String apellido, String mail, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Miembro() {}

    public Miembro(Integer id, String nombre, String apellido, String tipoDoc, int numDoc, ArrayList<Area> areas, ArrayList<Recorrido> recorridos, String mail, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.areas = areas;
        this.recorridos = recorridos;
        this.mail = mail;
        this.telefono = telefono;
    }

    public void registrarseA(Area area) {
        areas.add(area);
    }

    public void addRecorrido(Recorrido recorrido){
        recorridos.add(recorrido);
    }

    public void addRecorrido(ArrayList<Recorrido> recorridos){
        recorridos.addAll(recorridos);
    }
}
