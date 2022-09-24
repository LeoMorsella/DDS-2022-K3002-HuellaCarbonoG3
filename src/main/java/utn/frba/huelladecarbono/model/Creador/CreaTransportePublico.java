package utn.frba.huelladecarbono.model.Creador;

import org.springframework.beans.factory.annotation.Autowired;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoTransportePublico;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import utn.frba.huelladecarbono.repository.TransportePublicoRepository;

import java.util.ArrayList;

public class CreaTransportePublico {

    public static   TransportePublico creaTransportePublico(TipoTransportePublico tipoTransportePublico, String linea, ArrayList<Parada> paradas, String ID,TransportePublicoRepository t) {
        TransportePublico transportePublico = new TransportePublico(tipoTransportePublico,linea,paradas,ID);
        t.save(transportePublico);
        return transportePublico;

    }
}
