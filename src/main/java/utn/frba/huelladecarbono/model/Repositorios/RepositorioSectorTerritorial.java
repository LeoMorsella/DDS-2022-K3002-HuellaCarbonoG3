package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.SectorTerritorialController;
import utn.frba.huelladecarbono.controller.UsuarioController;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;

import java.util.ArrayList;

@Component
public class RepositorioSectorTerritorial {
    @Autowired
    SectorTerritorialController sectorTerritorialbd;
    private static RepositorioSectorTerritorial instance = new RepositorioSectorTerritorial();

    private ArrayList<SectorTerritorial> sectoresTerritoriales;

    private RepositorioSectorTerritorial() {
        this.sectoresTerritoriales = new ArrayList<>();
    }

    public ArrayList<SectorTerritorial> getSectoresTerritoriales() {
        return sectoresTerritoriales;
    }

    public static RepositorioSectorTerritorial getRepositorio() {
        return instance;
    }

    public void agregarSectorTerritorial(SectorTerritorial sectorTerritorial){
        this.sectoresTerritoriales.add(sectorTerritorial);
    }

    public void cargarDeUsuariosDeBdAlSistema() {
        for(SectorTerritorial sectorTerritorialbd : sectorTerritorialbd.getSectorTerritorial()) {
            SectorTerritorial sectorTerritorial= new SectorTerritorial();
            this.agregarSectorTerritorial(sectorTerritorial);
        }
    }

}
