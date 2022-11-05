package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioTrayectos;
import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Entity

public class Organizacion {

    @Transient
    @Autowired
    CreadorDeObjetos creadorDeObjetos;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String razonSocial;
    private Double huellaCarbono = 0.0;
    @Enumerated(EnumType.STRING)
    private TipoOrg tipo;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Ubicacion ubicacion;

    //TODO cambiar esto porque así no lo persiste
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Area> areas = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Miembro> contactosMail = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Miembro> contactosWP = new ArrayList<>();
    @Transient // Evaluar si es ElementCollection o Transient
    private List<Double> hcMensual = new ArrayList<>();
    private Double hcPromedio = 0.0;

  /*  @ManyToMany(fetch = FetchType.LAZY,
         cascade = {
                 CascadeType.PERSIST,
                 CascadeType.MERGE
         })
    @JoinTable(name = "organizacion_huellaCarbono",
         joinColumns = { @JoinColumn(name = "organizacion_id") },
         inverseJoinColumns = { @JoinColumn(name = "huellaCarbono_id") })**/
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HuellaCarbono> huellasCarbono = new ArrayList<>();

    private Boolean estaActivo;



    public Organizacion(Organizacion organizacionclase) {
    }



    public Organizacion(String razonSocial, TipoOrg tipo,Ubicacion ubicacion, Clasificacion clasificacion,List<Miembro> contactosMail, List<Miembro> contactosWP,List<Double> hcMensual, Double hcPromedio, List<HuellaCarbono> huellasCarbono, Double huellaCarbono,Boolean estaActivo) {

        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.clasificacion = clasificacion;
        this.contactosMail = contactosMail;
        this.contactosWP = contactosWP;
        this.hcMensual = hcMensual;
        this.huellaCarbono = huellaCarbono;
        this.hcPromedio = hcPromedio;
        this.huellasCarbono = huellasCarbono;
        this.estaActivo = estaActivo;
    }

    public Organizacion() {

    }

    public Area crearArea(String nombre, Organizacion organizacion, List<List<DatoDeMedicion>> mediciones){
        Area area = creadorDeObjetos.crearArea(nombre, organizacion, mediciones);
        this.areas.add(area);
        return area;
    }


    public String getRazonSocial() {
        return razonSocial;
    }

    public List<HuellaCarbono> getHuellasDeCarbono() {return huellasCarbono;}

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public TipoOrg getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrg tipo) {
        this.tipo = tipo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void agregarHuella(HuellaCarbono huella){
        this.huellasCarbono.add(huella);
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setHC(Double valor){
        this.huellaCarbono = valor;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public void setArea(Area area) {
        this.areas.add(area);
    }

    public void addArea(Area area) {
        this.areas.add(area);
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Miembro> getContactosMail() {
        return contactosMail;
    }

    public List<Miembro> getContactosWP() {
        return contactosWP;
    }

    public void setContactosWP(List<Miembro> contactosWP) {
        this.contactosWP = contactosWP;
    }

    public void setContactosMail(List<Miembro> contactos) {
        this.contactosMail = contactos;
    }
    public void agregarContactoMail(Miembro Contacto){
        contactosMail.add(Contacto);
    }

    public void agregarContactoWP(Miembro Contacto){
        contactosWP.add(Contacto);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }


    public Organizacion(String razonSocial, TipoOrg tipo, Ubicacion ubicacion, Clasificacion clasificacion, List<Miembro> contactosMail, List<Miembro> contactosWP) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.clasificacion = clasificacion;
        this.contactosMail = contactosMail;
        this.contactosWP = contactosWP;
    }

    public Organizacion(String razonSocial, TipoOrg tipo, Clasificacion clasificacion, List<Miembro> contactosMail, List<Miembro> contactosWP, Boolean estaActivo) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.contactosMail = contactosMail;
        this.contactosWP = contactosWP;
        this.estaActivo = estaActivo;
    }

    public List<Miembro> getMiembros(){
        List<Miembro> miembros = new ArrayList<>();
        for (Area area: areas){
            for (Miembro miembro: area.getMiembros()){
                miembros.add(miembro);
            }
        }
        return miembros;
    }

    public void generarTrayecto(Ubicacion salida, Ubicacion llegada, Medio medio){
        Trayecto nuevoTrayecto = new Trayecto(salida, llegada, medio);
        RepositorioTrayectos.getRepositorio().agregarTrayecto(nuevoTrayecto);
    }

    public List<List<DatoDeMedicion>> getMediciones(){
        List<List<DatoDeMedicion>> medicionesOrga = new ArrayList<>();
        for (Area area : this.areas){
            for (List<DatoDeMedicion> datoDeMedicion : area.getMediciones()){
                medicionesOrga.add(datoDeMedicion);
            }
        }
        return medicionesOrga;
    }
    public void setHCPromedio(Double valor) {
        this.hcPromedio = valor;
    }


    public Boolean estaActiva(){
        return this.getEstaActivo();
    }

    public double getHuellaTotal(){
        if(this.huellaCarbono == null){
        Double huellaCTotal = this.huellasCarbono.stream()
                .map(huellaCarbono -> huellaCarbono.getHuella())
                .collect(Collectors.summingDouble(Double::doubleValue));
        this.huellaCarbono = huellaCTotal;
        }
        return this.huellaCarbono;
    }

    @Override
    public String toString() {
        return "Organizacion{" +
                "id=" + id +
                ", razonSocial='" + razonSocial + '\'' +
                ", huellaCarbono=" + huellaCarbono +
                ", tipo=" + tipo +
                ", ubicacion=" + ubicacion +
                ", areas=" + areas +
                ", clasificacion=" + clasificacion +
                ", contactosMail=" + contactosMail +
                ", contactosWP=" + contactosWP +
                ", hcMensual=" + hcMensual +
                ", hcPromedio=" + hcPromedio +
                ", huellasCarbono=" + huellasCarbono +
                ", estaActivo=" + estaActivo +
                '}';
    }
}
