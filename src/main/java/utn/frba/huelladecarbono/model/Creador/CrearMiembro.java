package utn.frba.huelladecarbono.model.Creador;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.RecorridoRepository;

import java.util.Calendar;

public class CrearMiembro {
    public static Miembro crearMiembro(int id, String nombre, String apellido, String mail, String telefono,Boolean estaActivo, MiembroRepository m) {
        Miembro miembro = new Miembro(id,nombre,apellido,mail,telefono,estaActivo);
        m.save(miembro);
        // RepositorioRecorrido.getRepositorio().agregarRecorrido(recorrido);
        return miembro;
    }
}
