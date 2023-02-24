package utn.frba.huelladecarbono;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.frba.huelladecarbono.service.CalculoDeHuellaServiceV2.TareaMensual;

import java.io.IOException;
import java.util.Timer;

@SpringBootApplication
public class HuellaDeCarbonoApplication implements CommandLineRunner {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(HuellaDeCarbonoApplication.class, args);

		Timer timer = new Timer();
		timer.schedule(new TareaMensual(), 0, 86400000);

	}

	@Override
	public void run(String... args) throws Exception {

	}

}