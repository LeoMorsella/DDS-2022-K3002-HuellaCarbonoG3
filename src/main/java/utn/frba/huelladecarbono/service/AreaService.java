package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.repository.AreaRepository;
import utn.frba.huelladecarbono.repository.MedioMotorizadoRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;



}
