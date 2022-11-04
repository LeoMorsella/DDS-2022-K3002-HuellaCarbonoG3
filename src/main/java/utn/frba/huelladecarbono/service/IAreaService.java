package utn.frba.huelladecarbono.service;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.List;

public interface IAreaService {

    //metodo para obtener organizacion por ID
    public Area findById(Integer id)throws Exception;

    //metodo para obtener a todas las organizaciones
    public List<Area> getAreas();

    //Metodo para dar de alta a una organizacion
    public void saveArea(Area area);

    //Metodo para eliminar una organizacion
    public void deleteArea(Integer id);

    //Metodo para encontrar a una organizacion
    public Area findArea(Integer id);

    //Metodo para encontrar a una organizacion por su estado
    public List<Area> findAreaByEstadoActivo();

    public void cambiarEstadoArea(Integer id);

    public Area modificarArea(Integer id, Area area);

    public Area crearArea(Area area);
}
