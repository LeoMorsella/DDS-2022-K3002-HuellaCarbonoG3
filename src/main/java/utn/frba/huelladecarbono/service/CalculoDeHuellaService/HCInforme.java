package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

public class HCInforme {
    public String nombre;
    public Double valor;

    public HCInforme(String nombre, Double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
