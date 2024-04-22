package expeditors.backend;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.FilterDTO;
import expeditors.backend.adoption.TypeFilter;
import expeditors.backend.adoption.TypePet;
import expeditors.backend.config.AppConfig;
import expeditors.backend.service.AdopterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {AdoptionApplication.class})
@ActiveProfiles({"dev"})
@SpringBootTest
class AdoptionApplicationTests {
	@Autowired
	private AdopterService adopterService;

	private void initializeRegisters(AdopterService adopterServiceInstance){
		LocalDate today = LocalDate.now();

		Adopter adopter = new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(15), TypePet.Dog);
		adopterServiceInstance.addAdopter(adopter);

		Adopter adopter2 = new Adopter(1,"Karina Mtz", "111-004-66",today.plusDays(5), TypePet.Cat);
		adopterServiceInstance.addAdopter(adopter2);

		Adopter adopter3 = new Adopter(1,"Selene Hernandez", "700-010-66",today.plusDays(10), TypePet.Turtle);
		adopterServiceInstance.addAdopter(adopter3);
	}

	@Test
	public void addAdopterTest() {
		Adopter adopter = new Adopter(1,"Melisa Lopez", "111-004-66",null, TypePet.Dog);
		assertNotNull(adopterService.addAdopter(adopter));
	}
	@Test
	public void deleteAdopterTest() {
		initializeRegisters(adopterService);
		assertTrue(adopterService.deleteAdopter(1));
	}

	@Test
	public void updateAdopterTest() {
		initializeRegisters(adopterService);

		Adopter adopterUpdate = new Adopter(1,"Melisa Lopez Garcia", "111-004-66",null, TypePet.Dog);

		adopterService.getAllAdopters().forEach(System.out::println);
		assertTrue(adopterService.updateAdopter(adopterUpdate));
		adopterService.getAllAdopters().forEach(System.out::println);

	}

	@Test
	public void findByIdTest() {
		initializeRegisters(adopterService);

		assertNotNull(adopterService.getAdopterById(2));
	}

	@Test
	public void getAllAdoptersTest() {
		initializeRegisters(adopterService);

		assertFalse(adopterService.getAllAdopters().isEmpty());
	}

	@Test
	public void getAdopterFilterByNameTest() {
		initializeRegisters(adopterService);

		assertNotNull(adopterService.getAdopterFilterByName("mtz"));
	}


	@Test
	public void getOrderByDateTest() {;
		initializeRegisters(adopterService);
		adopterService.getAllAdopters().forEach(System.out::println);
		System.out.println("------------------------------------------------------------------------------");
		List<Adopter> result = adopterService.getOrderByDate();
		result.forEach(System.out::println);

		Adopter minDate = result.stream().min(Comparator.comparing(Adopter::getDateAdoption)).orElseThrow(NoSuchElementException::new);

		Adopter maxDate = result.stream().max(Comparator.comparing(Adopter::getDateAdoption)).orElseThrow(NoSuchElementException::new);


		assertTrue(minDate.getDateAdoption().isBefore(maxDate.getDateAdoption()));
	}

	@Test
	public void findByTest() {
		initializeRegisters(adopterService);

		List<FilterDTO> filterDTOList = new ArrayList<>();
		filterDTOList.add(new FilterDTO(TypeFilter.NAME, "Hdz"));
		filterDTOList.add(new FilterDTO(TypeFilter.TYPEPET, TypePet.Cat));

		assertNotNull(adopterService.findBy(filterDTOList));
	}

}
