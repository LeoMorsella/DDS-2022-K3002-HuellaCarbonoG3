package utn.frba.huelladecarbono.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.RegistroCalculoHCDatoActividad;


import java.util.List;

@Repository
public interface DatoActividadRepository extends JpaRepository<RegistroCalculoHCDatoActividad,Integer> {


}