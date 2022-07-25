package utn.frba.huelladecarbono.repository;

import utn.frba.huelladecarbono.model.ManejoAmbiental.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Integer> {
}
