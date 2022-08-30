package utn.frba.huelladecarbono.service.CalculoDeHuellaService;


import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalcularHuellaDeCarbonoService {

    Double k = 0.0;
    private static CalcularHuellaDeCarbonoService instance = new CalcularHuellaDeCarbonoService();

    public Double getK() {
        return k;
    }

    public static CalcularHuellaDeCarbonoService getCalculadora() {
        return instance;
      }

    public void setK(Double k) {
        this.k = k;
    }

    /* public CalcularHuellaDeCarbono(Double k) {
        this.k = k;
    } */

    public CalcularHuellaDeCarbonoService(){
        this.k = 0.0;
    }

    //Todos los calculos de HC se hacen respecto a un mes de consumo
    public Double calcularHCMedicion(List<DatoDeMedicion> datoDeMedicion) {
        return CalcularHuellaDeCarbonoMedicion.calcularHCMedicion(datoDeMedicion, k);
    }

    public Double calcularHC(Miembro miembro) throws Exception {
        return CalcularHuellaDeCarbonoMiembro.calcularHCMiembro(miembro);
    }

    public Double calcularHC(Organizacion organizacion){
        Double HC = 0.0;

        for( Area area : organizacion.getAreas()) {
            HC += this.calcularHC(area);
        }
        return HC;
    }

    public Double calcularHC(Area area){
        Double HC = 0.0;
        for (Miembro miembro : area.getMiembros()) {
            try {
                HC += this.calcularHC(miembro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (List<DatoDeMedicion> mediciones :area.getMediciones() ) {
            HC += this.calcularHCMedicion(mediciones);
        }
        return HC;
    }

    public Double calcularHC(SectorTerritorial st) throws Exception{
        Double HCTotal = 0.0;
        for (Organizacion organizacion: st.getOrganizaciones()){
            HCTotal += organizacion.calcularHC();
        }
        return HCTotal;
    }

    public Double HCpromedio(Organizacion organizacion) throws Exception {
        return calcularHC(organizacion) / organizacion.getMiembros().size();
    }

    public Double HCpromedio(Area area) throws Exception {
        return calcularHC(area) / area.getMiembros().size();
    }

    //Falta Implementar peso
    public Double HCTrayecto(Trayecto trayecto) throws Exception {
        Double HC = 0.0;
        Date fechaI = trayecto.getFechaDeInicio();
        Date fechaF = trayecto.getFechaDeFin();
        int mesInicio = fechaI.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        int mesFin = fechaF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        int cantMeses = mesFin - mesInicio;
        for(int i= 0; i<cantMeses;i++) {
            List<Miembro> pasajeros = trayecto.getPasajeros();
            for(Miembro pasajero: pasajeros) {
                HC += calcularHCMensual(pasajero);

            }
        }
        return HC;
    }

    //TODO Para calculo de HC mensual

    public Double calcularHCMensual(Miembro miembro) throws Exception {
        return 4.5 * CalcularHuellaDeCarbonoMiembro.calcularHCMiembro(miembro);
    }
}
