package expeditors.backend;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.TypePet;
import expeditors.backend.service.AdopterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@Configuration
//@ComponentScan({"expeditors.backend", "expeditors.backend.application"})
//@EnableAutoConfiguration
public class AdoptionApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdoptionApplication.class, args);
	}
}

@Component
class GoCmdLine implements CommandLineRunner
{
	//@Autowired
	final
	AdopterService adopterService;

	GoCmdLine(AdopterService adopterService) {
		this.adopterService = adopterService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Run Spring Boot");

		adopterService.addAdopter(new Adopter.AdopterBuilder()
				.name("Luis")
				.phoneNumber("106-150-11")
				.typePet(TypePet.Turtle)
				.namePet("Rocky")
				.breedPet("NA")
				.build());
		adopterService.addAdopter(new Adopter.AdopterBuilder()
				.name("Melisa")
				.phoneNumber("996-150-11")
				.typePet(TypePet.Cat)
				.namePet("Pelusa")
				.breedPet("NA")
				.build());

		adopterService.getAllAdopters().forEach(System.out::println);

	}
}
