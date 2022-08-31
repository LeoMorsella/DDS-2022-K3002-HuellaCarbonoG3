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

    public static boolean estaEntre(Calendar fechaInicio, Calendar fechaFin, Calendar fecha) {
        return fecha.after(fechaInicio) && fecha.before(fechaFin);
    }
}
