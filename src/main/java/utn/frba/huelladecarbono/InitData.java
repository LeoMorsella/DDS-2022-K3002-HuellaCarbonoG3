package utn.frba.huelladecarbono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Distancia;
import utn.frba.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import utn.frba.huelladecarbono.model.MedioDeTransporte.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.*;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;
import utn.frba.huelladecarbono.service.UbicacionService;

import java.time.LocalDate;
import java.util.*;

@Component
public class InitData implements CommandLineRunner {



    @Autowired
    OrganizacionRepository repoOrganizacion;

    @Autowired
    RepositorioOrganizaciones repositorioOrganizaciones;

    @Autowired
    OrganizacionController organizacionController;

    //Usuarios
    @Autowired
    UsuarioRepository repoUsuario;

    @Autowired
    RecorridoRepository repoRecorrido;
    

    //Paradas
    @Autowired
    ParadaRepository repoParadas;

    //Sector
    @Autowired
    SectorTerritorialRepository repoSectores;

    //Transporte

    @Autowired
    TransportePublicoRepository repoTransportes;

    @Autowired
    MedioNoMotorizadoRepository repoMedioNoMotorizado;

    @Autowired
    MedioMotorizadoRepository repoMedioMotorizado;

    @Autowired
    MiembroRepository repoMiembros;

    @Autowired
    UbicacionRepository repoUbicaciones;

    @Autowired
    AreaRepository repoAreas;

    @Autowired
    TrayectoRepository repoTrayectos;

    @Autowired
    RepositoryRestConfiguration config;

    @Autowired

    CreadorDeObjetos creadorDeObjetos;



    @Override
    public void run(String... args) throws Exception {

        cargarMedioNoMotorizado();
        cargarMedioMotorizado();
        cargarOrganizaciones();
        cargarMiembros();
        cargarUsuarios();
        cargarRecorridos();
        cargarSectores();
        cargarParadas();
        cargarUbicaciones();
        cargarAreas();
        cargarTrayectos();
        cargarTransportePublico();
        actualizarOrganizacion();
        darDeBajaOrganizacion();


    }

    //TODO Falla de recorridos

   public void cargarRecorridos() throws Exception
    {
        config.exposeIdsFor(Recorrido.class);
        if(repoRecorrido.count() == 0) {
            Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
            creadorDeObjetos.crearRecorrido(organizacion1,0.50, Calendario.crearFecha(1, 2020), Calendario.crearFecha(9, 2022));
            //De esta manera se carga el recorrido
            Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
            Recorrido recorridoPruebaDos =creadorDeObjetos.crearRecorrido(organizacion3,0.8,Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021));
        }
        else{
            System.out.println("Ya existen Recorridos creados anteriormente");
        }
    }

    public void cargarParadas() throws Exception
    {
        config.exposeIdsFor(Usuario.class);
        if(repoParadas.count() == 0) {

            Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            Parada paradaPruebaUno = creadorDeObjetos.crearParada("120", ubicacionPruebaUno);
            Parada paradaPruebaDos = creadorDeObjetos.crearParada("50",ubicacionPruebaUno);
        }
        else{
            System.out.println("Ya existen Paradas creados anteriormente");
        }
    }

    public void cargarUbicaciones() throws Exception
    {
            config.exposeIdsFor(Ubicacion.class);
            if(repoUbicaciones.count()==0) {
                Ubicacion ubicacion1 = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
                List<Ubicacion> ubicaciones = List.of(ubicacion1);
                ubicaciones.stream().forEach(ubicacion -> {repoUbicaciones.save(ubicacion);});
            }
            else {
                System.out.println("Ya existen ubicaciones creadas anteriormente");
            }
    }

    public void cargarSectores() {
        config.exposeIdsFor(SectorTerritorial.class);
        if(repoSectores.count() == 0){
            AgenteSectorial agente = new AgenteSectorial();
            SectorTerritorial sector = creadorDeObjetos.crearSectorTerritorial("Almirante Brown","Buenos Aires", agente);
        }
        else{
            System.out.println("Ya existen sectores territoriales creados anteriormente");
        }
    }

   public void cargarUsuarios() throws Exception
    {
        config.exposeIdsFor(Usuario.class);
        if(repoUsuario.count() == 0) {

            Usuario usuario1 = new Usuario("prueba", "Yagni3210+", Arrays.asList(new Rol("ROLE_USER")));
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuario1);
            usuarios.stream().forEach(usuario -> {
                repoUsuario.save(usuario);
            });
        }
        else{
            System.out.println("Ya existen Usuarios creados anteriormente");
        }
    }

    public void cargarMedioNoMotorizado() throws Exception
    {
        config.exposeIdsFor(MedioNoMotorizado.class);
        if(repoMedioNoMotorizado.count()==0) {
            MedioNoMotorizado medio1 = new MedioNoMotorizado(TipoMedioNoMotorizado.A_PIE);
            List<MedioNoMotorizado> mediosNoMotorizados = List.of(medio1);
            mediosNoMotorizados.stream().forEach(medio -> {
                repoMedioNoMotorizado.save(medio);
            });
        }
    }

    public void cargarTransportePublico() throws Exception {
        config.exposeIdsFor(TransportePublico.class);
        if(repoTransportes.count()==0) {
            List<Parada> paradas = new ArrayList<>();
            Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            Parada paradaPrueba3 = creadorDeObjetos.crearParada("120", ubicacionPruebaUno);
            paradas.add(paradaPrueba3);
            TransportePublico transporte1 = new TransportePublico(TipoTransportePublico.COLECTIVO,"88", paradas,"123456");
            List<TransportePublico> transportePublicos = List.of(transporte1);
            transportePublicos.stream().forEach(transporte -> {
                repoTransportes.save(transporte);
            });

        }
    }

    public void cargarMedioMotorizado() throws Exception
    {
        config.exposeIdsFor(MedioMotorizado.class);
        if(repoMedioMotorizado.count()==0) {

            MedioMotorizado medio1 = new MedioMotorizado(TipoVehiculoMotorizado.MOTO,TipoCombustible.NAFTA,"FRX123",Boolean.FALSE,"Particular");
            List<MedioMotorizado> mediosMotorizados = List.of(medio1);
            mediosMotorizados.stream().forEach(medio -> {
                repoMedioMotorizado.save(medio);
            });
        }
    }

    public void cargarAreas() throws Exception {
        config.exposeIdsFor(Area.class);
        if(repoAreas.count() == 0) {
            Area area1 = new Area("Gonzalez Catan",null,null);
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
            area1.setOrganizacion(organizacion1);
            List<Area> areas = List.of(area1);
            areas.stream().forEach(area -> {
                repoAreas.save(area);
            });
        }

    }

    //TODO evaluar si el formato JSON devuelto es valido
    public void cargarTrayectos() throws Exception {
        config.exposeIdsFor(Area.class);
        cargarMedioMotorizado();
        if(repoTrayectos.count() == 0) {
            Trayecto trayecto1 = new Trayecto();
            Ubicacion ubicacion1 = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            Ubicacion ubicacion2 = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            List<Ubicacion> ubicaciones = List.of(ubicacion1);
            ubicaciones.stream().forEach(ubicacion -> {repoUbicaciones.save(ubicacion);});
            MedioMotorizado medio1 = new MedioMotorizado(TipoVehiculoMotorizado.MOTO,TipoCombustible.NAFTA,"FRX123",Boolean.FALSE,"Particular");
            trayecto1.setId(12);
            trayecto1.setMedioTransporte(medio1);
            trayecto1.setPuntoPartida(ubicacion1);
            trayecto1.setPuntoLlegada(ubicacion2);
            List<Trayecto> trayectos = List.of(trayecto1);
            List<MedioMotorizado> medios = List.of(medio1);
            medios.stream().forEach(medio -> repoMedioMotorizado.save(medio));
            trayectos.stream().forEach(trayecto -> repoTrayectos.save(trayecto));

        }
    }

    //Solo a modo prueba es esto

    public void cargarMiembros() throws Exception
    {
        config.exposeIdsFor(Miembro.class);
        if(repoMiembros.count() == 0) {

            Miembro miembroPruebaUno = creadorDeObjetos.crearMiembro(20,"Pablo","Ortiz","pablo@prueba","2323",true);
        }
        else{
            System.out.println("Ya existen Miembros creados anteriormente");
        }
    }

    public void cargarOrganizaciones() throws Exception
    {
        config.exposeIdsFor(Organizacion.class);
        if(repoOrganizacion.count() == 0) {

            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
            Organizacion organizacion2 = creadorDeObjetos.crearOrganizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
            Organizacion organizacion3 = creadorDeObjetos.crearOrganizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
            Organizacion organizacion4 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);
            Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            ArrayList<Double> listaHCPrueba = new ArrayList<>();
            Area area1 = creadorDeObjetos.crearArea("AreaPrueba", organizacion1);
            listaHCPrueba.add(100.00);
            HuellaCarbono huellaPrueba = new HuellaCarbono(Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021), 250.00);
            List<HuellaCarbono> hashMapPrueba = new ArrayList<>();
            hashMapPrueba.add(huellaPrueba);
            Organizacion organizacionConHC = creadorDeObjetos.crearOrganizacionConHC("SA",TipoOrg.INSTITUCION,ubicacionPruebaUno,Clasificacion.EMPRESA_SECTOR_SECUNDARIO,null,null,listaHCPrueba,250.00,hashMapPrueba,500.00,false);
            Miembro miembroPruebaMail = creadorDeObjetos.crearMiembro(50,"Gonza","D","mail@prueba","5511",true);
            Miembro miembroPruebaWP = creadorDeObjetos.crearMiembro(50,"Gonza2","D","mail2@prueba","221",true);
            organizacion1.agregarContactoWP(miembroPruebaWP);
            organizacion1.agregarContactoMail(miembroPruebaMail);
        }
        else{
            System.out.println("Ya existen Organizaciones creados anteriormente");
        }
    }

    public void actualizarOrganizacion() throws Exception
    {
        Organizacion nuevaOrganizacion = repoOrganizacion.findById(1).get();
        nuevaOrganizacion.setRazonSocial("OrganizacionPableken!");
        organizacionController.actualizarOrganizacion(1,nuevaOrganizacion);
    }

    public void darDeBajaOrganizacion() throws Exception
    {
        organizacionController.deleteOrganizacion(3);
    }


}

