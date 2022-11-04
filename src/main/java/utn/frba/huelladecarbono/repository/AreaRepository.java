package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;

public interface AreaRepository extends JpaRepository<Area,Integer> {
}
