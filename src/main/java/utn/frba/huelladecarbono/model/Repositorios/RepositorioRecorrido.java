package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.RecorridoController;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioRecorrido {

    @Autowired
    RecorridoController recorridobd;
    private static RepositorioRecorrido instance = new RepositorioRecorrido();
    private List<Parada> recorridos;
    private ArrayList<Recorrido> recorridoArrayList;

    private RepositorioRecorrido() {
        this.recorridos = new ArrayList<>();

    }

    public List<Parada> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(List<Parada> recorridos) {
        this.recorridos = recorridos;
    }

    public static RepositorioRecorrido getRepositorio() {
        return instance;
    }

    public void agregarRecorrido (Recorrido recorrido) {
            recorridoArrayList.add(recorrido);
    }

    public void cargarDeRecorridoDeBdAlSistema() {
        for(Recorrido recorridoclase : recorridobd.getRecorridos()) {
            Recorrido recorrido = new Recorrido(recorridoclase.getOrganizacion(),recorridoclase.getPeso(),recorridoclase.getMesInicio(),recorridoclase.getMesFin());
            this.agregarRecorrido(recorrido);
        }
    }

}
