package utn.frba.huelladecarbono.service.CalculoDeHuellaServiceV2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCOrganizacion;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Registro;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

@Component
public class TareaMensual extends TimerTask {
    @Autowired
    OrganizacionRepository repoOrg;
    @Autowired
    MiembroRepository repoMiembro;
    @Override
    public void run() {
        Calendar now = Calendar.getInstance();

        if (now.get(Calendar.DAY_OF_MONTH) == 1) {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaAnterior = fechaActual.minusMonths(1);
            LocalDate primerDiaMesAnterior = fechaAnterior.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate ultimoDiaMesAnterior = fechaAnterior.with(TemporalAdjusters.lastDayOfMonth());
            List<Organizacion> orgs = repoOrg.findAll();

            for (Organizacion org : orgs) {
                Double hc = CalculadoraHCOrganizacion.calcularHC(org, primerDiaMesAnterior, ultimoDiaMesAnterior);
                org.agregarRegistroMensual(new Registro(fechaAnterior, hc));
                org.limpiarMediciones();
            }

        }
    }
}
