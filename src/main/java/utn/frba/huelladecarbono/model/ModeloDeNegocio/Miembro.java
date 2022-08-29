package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalcularHuellaDeCarbonoService;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public ArrayList<Recorrido> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(ArrayList<Recorrido> recorridos) {
        this.recorridos = recorridos;
    }

    public Miembro(String nom, String ape, String tipoDocu, int numeroDoc, ArrayList<Area> listaAreas,
                   ArrayList<Recorrido> unRecorrido){
        this.nombre = nom;
        this.apellido = ape;
        this.tipoDoc = tipoDocu;
        this.numDoc = numeroDoc;
        this.areas = listaAreas;
        this.recorridos = unRecorrido;
        RepositorioMiembros.getRepositorio().agregarMiembro(this);
    }

    public Miembro(Integer id, String nombre, String apellido ,String mail, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Miembro() {
    }

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

    public Boolean perteneceA(Area area) {return areas.contains(area);}

    public Double calcularHC() throws RuntimeException {
        try {
            return CalcularHuellaDeCarbonoService.getCalculadora().calcularHC(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Meter en service quizas
    public Double calcularImpactoIndividual(Organizacion organizacion) throws Exception {
        Double HCInd;
        Double HCorg;
        Double promedio;
        Double impacto;
    //  Double k = CalcularHuellaDeCarbono.getCalculadora().getK();

        HCorg = organizacion.calcularHC();
        //TODO: Asociar recorridos a las organizaciones donde se usan
        HCInd = this.calcularHC();
        promedio = HCorg / organizacion.getMiembros().size();
        impacto = (HCInd * promedio) / 100;
        
        System.out.println("La Huella de carbono de su compañía es: " + HCorg +". Su Huella de carbono"
        +" individual es: " + HCInd + ". Eso equivale a un: " +impacto+ "% de la Huella de carbono de " +
                "organización");
        return impacto;
    }

}
