package utn.frba.huelladecarbono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.MiembroController;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.controller.ParadaController;
import utn.frba.huelladecarbono.controller.UsuarioController;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Distancia;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioParadas;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioUsuarios;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.*;

import java.util.List;

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
    RepositorioUsuarios repositorioUsuarios;

    @Autowired
    UsuarioController usuarioController;

    //Paradas
    @Autowired
    ParadaRepository repoParadas;

    @Autowired
    RepositorioParadas repositorioParadas;
    @Autowired
    ParadaController paradaController;

    //Transporte
    @Autowired
    MiembroRepository repoMiembros;

    @Autowired
    RepositorioMiembros repositorioMiembros;
    @Autowired
    MiembroController miembroController;

    @Autowired
    RepositoryRestConfiguration config;

    @Override
    public void run(String... args) throws Exception
    {
        config.exposeIdsFor(Organizacion.class);
        if(repoOrganizacion.count() == 0 && repoUsuario.count() == 0) {
            Organizacion organizacion1= new Organizacion("SA", TipoOrg.EMPRESA,Clasificacion.MINISTERIO, null, null,true);
            Organizacion organizacion2 = new Organizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null,false);
            Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null,true);
            Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null,false);
            Usuario usuario1 = new Usuario("prueba","Yagni3210+", Rol.MIEMBRO);


            List<Organizacion> organizaciones = List.of(organizacion1,organizacion2,organizacion3,organizacion4);

            organizaciones.stream().forEach(organizacion->{
                repoOrganizacion.save(organizacion);
            });
            System.out.println("*******************************************");
            repositorioOrganizaciones.cargarDeOrganizacionesDeBdAlSistema();
            //Prueba para mostrar las organizaciones activas
            System.out.println(repositorioOrganizaciones.getOrganizaciones());
            //Prueba para mostrar todas las organizaciones
            System.out.println(organizacionController.getOrganizaciones());

            List<Usuario> usuarios = List.of(usuario1);
            usuarios.stream().forEach(usuario -> {repoUsuario.save(usuario);});
            System.out.println("*******************************************");
            repositorioUsuarios.cargarDeUsuariosDeBdAlSistema();
            //Prueba para mostrar las organizaciones activas
            System.out.println(repositorioUsuarios.getUsuarios());
            //Prueba para mostrar todas las organizaciones
            System.out.println(usuarioController.getUsuarios());

            //cargarMiembros();
            Distancia distanciaPrueba = new Distancia();
            Ubicacion ubicacionPruebaUno = new Ubicacion("Argnentina","algo","Muni","Barracas","uspallata","2020");
            Parada paradaPruebaUno = new Parada("120",ubicacionPruebaUno,distanciaPrueba);
           //Parada paradaPruebaDos = new Parada("50",ubicacionPruebaUno);
            List<Parada> paradas = List.of(paradaPruebaUno);
            paradas.stream().forEach(parada->{repoParadas.save(parada);});
            System.out.println("*******************************************");
            repositorioParadas.cargarDeParadaDeBdAlSistema();
            //Prueba para mostrar las organizaciones activas
            System.out.println(repositorioParadas.getParadas());
            //Prueba para mostrar todas las organizaciones
            System.out.println(paradaController.getParadas());
        }

        else{
            //Para que se creen las organizaciones cada vez que corremos el programa hay que cambiar el valor de "update" por "create"
            //Dependiendo de que como se inicie la bd
            //Con Spring boot  ->  spring.jpa.hibernate.ddl-auto=update
            // o
            // Con el xml  ->     <property name="hibernate.hbm2ddl.auto" value="update"/>
                System.out.println("Ya existen Organizaciones creadas anteriormente");
            }
        }

    public void cargarMiembros() throws Exception
    {
        config.exposeIdsFor(Organizacion.class);
        if(repoMiembros.count() == 0 && repoMiembros.count() == 0) {

            Miembro miembroPruebaUno = new Miembro(20,"Pablo","Ortiz","pablo@prueba","2323");
            List<Miembro> miembros = List.of(miembroPruebaUno);
            miembros.stream().forEach(parada->{repoMiembros.save(miembroPruebaUno);});
            System.out.println("*******************************************");
            repositorioMiembros.cargarDeMiembrosDeBdAlSistema();
            //Prueba para mostrar las organizaciones activas
            System.out.println(repositorioMiembros.getMiembros());
            //Prueba para mostrar todas las organizaciones
            System.out.println(miembroController.getMiembros());

        }

        else{
            //Para que se creen las organizaciones cada vez que corremos el programa hay que cambiar el valor de "update" por "create"
            //Dependiendo de que como se inicie la bd
            //Con Spring boot  ->  spring.jpa.hibernate.ddl-auto=update
            // o
            // Con el xml  ->     <property name="hibernate.hbm2ddl.auto" value="update"/>
            System.out.println("Ya existen Organizaciones creadas anteriormente");
        }
    }
}
