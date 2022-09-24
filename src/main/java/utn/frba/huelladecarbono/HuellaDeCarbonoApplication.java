package utn.frba.huelladecarbono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.AutenticacionResponse;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.FactoresDeEmision;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootApplication

public class HuellaDeCarbonoApplication implements CommandLineRunner {

	@Autowired
    RepositorioOrganizaciones repositorioOrganizaciones;
	public static void main(String[] args) throws IOException {


		SpringApplication.run(HuellaDeCarbonoApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {

	}

}