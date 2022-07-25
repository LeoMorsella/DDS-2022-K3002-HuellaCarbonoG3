package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ManejoAmbiental.Miembro;
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
}