package utn.frba.huelladecarbono;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    OrganizacionRepository repoOrganizacion;

    @Autowired
    RepositorioOrganizaciones repositorioOrganizaciones;

    @Autowired
    OrganizacionController organizacionController;

    @Autowired
    RepositoryRestConfiguration config;

    @Override
    public void run(String... args) throws Exception
{
        config.exposeIdsFor(Organizacion.class);
        if(repoOrganizacion.count() == 0) {
        Organizacion organizacion1= new Organizacion("SA", TipoOrg.EMPRESA,Clasificacion.MINISTERIO, null, null,true);
        Organizacion organizacion2 = new Organizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null,false);
        Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null,true);
        Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null,false);

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
