package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.repository.MiembroRepository;

import java.util.List;

@Service
public class MiembroService implements IMiembroService {

    @Autowired
    private MiembroRepository miembroRepository;


    @Override
    public List<Miembro> getMiembros() {
        List <Miembro> listaMiembros = miembroRepository.findAll();

        return listaMiembros;
    }

    @Override
    public void saveMiembro(Miembro miembro) {
        miembroRepository.save(miembro);
    }

    @Override
    public void deleteMiembro(Integer id) {
        miembroRepository.deleteById(id);
    }

    @Override
    public Miembro findMiembro(Integer id) {
        Miembro miembro = miembroRepository.findById(id).orElse(null);
        return miembro;
    }

    public Miembro modificarMiembro(Integer id, Miembro miembro){
        Miembro miembroActualizado = this.findMiembro(id);
        miembroActualizado.setNombre(miembro.getNombre());
        miembroActualizado.setApellido(miembro.getApellido());
        miembroActualizado.setTipoDoc(miembro.getTipoDoc());
        miembroActualizado.setAreas(miembro.getAreas());
        miembroActualizado.setMail(miembro.getMail());
        miembroActualizado.setTelefono(miembro.getTelefono());
        miembroActualizado.setEstaActivo(miembro.getEstaActivo());
        this.saveMiembro(miembroActualizado);
        return miembroActualizado;

        }

    @Override
    public void cambiarEstadoMiembro(Integer id) {
        Miembro miembro = findMiembro(id);
        miembro.setEstaActivo(false);

        this.saveMiembro(miembro);
    }
}