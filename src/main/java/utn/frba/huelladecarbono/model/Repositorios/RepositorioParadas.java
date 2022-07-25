package utn.frba.huelladecarbono.model.Repositorios;

import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;

import java.util.ArrayList;
import java.util.List;

public class RepositorioParadas {
  private static RepositorioParadas instance = new RepositorioParadas();
  private List<Parada> paradas;


  private RepositorioParadas() {
    this.paradas = new ArrayList<>();

  }

  public List<Parada> getParadas() {
    return paradas;
  }

  public void setParadas(List<Parada> paradas) {
    this.paradas = paradas;
  }

  public static RepositorioParadas getRepositorio() {
    return instance;
  }

  public void agregarParada(Parada par){
    this.paradas.add(par);
  }
}
