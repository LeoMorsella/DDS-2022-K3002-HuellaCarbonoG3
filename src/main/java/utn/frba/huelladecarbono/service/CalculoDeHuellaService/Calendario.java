package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import java.util.Calendar;

public class Calendario {

    public static Calendar crearFecha(int mes, int anio) {
        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.MONTH, mes);
        fecha.add(Calendar.YEAR, anio);
        return fecha;
    }

    public static Calendar sinFecha() {
        return null;
    }

    public static Calendar crearAnio(int anio) {
        Calendar anioC = Calendar.getInstance();
        anioC.add(Calendar.YEAR, anio);
        return anioC;
    }

    public static Calendar fechaActual() {
        return Calendar.getInstance();
    }

    public static int mesesEntreMedio(Calendar inicioCalculo, Calendar finCalculo, Calendar inicioRecorrido, Calendar finRecorrido) {
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        if(finRecorrido == null) {
            finRecorrido = Calendar.getInstance();
        }

        if(inicioCalculo.before(finRecorrido) && finCalculo.after(inicioRecorrido)) {
            if(inicioCalculo.before(inicioRecorrido)) {
                inicio = inicioCalculo;
            } else {
                inicio = inicioRecorrido;
            }

            if(finCalculo.after(finRecorrido)) {
                fin = finCalculo;
            } else {
                fin = finRecorrido;
            }

            int diferenciaAnios = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
            return diferenciaAnios * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        } else {
            return 0;
        }
    }
}
