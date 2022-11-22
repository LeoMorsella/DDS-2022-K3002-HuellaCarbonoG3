package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion,Integer> {
}
