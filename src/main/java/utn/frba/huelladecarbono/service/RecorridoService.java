package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.repository.RecorridoRepository;

import java.util.List;

@Service
public class RecorridoService implements IRecorridoService{

    @Autowired
    private RecorridoRepository recorridoRepository;


    @Override
    public List<Recorrido> getRecorridos() {
        List <Recorrido> listaRecorridos = recorridoRepository.findAll();

        return listaRecorridos;
    }

    @Override
    public void saveRecorrido(Recorrido recorrido) {
        recorridoRepository.save(recorrido);
    }

    @Override
    public void deleteRecorrido(Integer id) {
        recorridoRepository.deleteById(id);
    }

    @Override
    public Recorrido findRecorrido(Integer id) {
        Recorrido recorrido = recorridoRepository.findById(id).orElse(null);
        return recorrido;
    }


}
