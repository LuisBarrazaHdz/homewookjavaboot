package expeditors.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.TypePet;
import expeditors.backend.dao.AdopterDAO;
import expeditors.backend.service.AdopterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ActiveProfiles({"dev"})
@ExtendWith(MockitoExtension.class)
public class AdoptionApplicationUnitTest {
    @Mock
    private AdopterDAO adopterDAO;

    @InjectMocks
    private AdopterService adopterService;

    /*
    List<Adopter> adopters = new ArrayList<>();

    {
        initializeRegisters();
    }


    private void initializeRegisters(){
        LocalDate today = LocalDate.now();

        Adopter adopter = new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(15), TypePet.Dog);
        adopters.add(adopter);

        Adopter adopter2 = new Adopter(1,"Karina Mtz", "111-004-66",today.plusDays(5), TypePet.Cat);
        adopters.add(adopter2);

        Adopter adopter3 = new Adopter(1,"Selene Hernandez", "700-010-66",today.plusDays(10), TypePet.Turtle);
        adopters.add(adopter3);
    }
    */

    @Test
    public void addAdopterTest() {
        LocalDate today = LocalDate.now();
        List<Adopter> adopterList = List.of(new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(15), TypePet.Dog),
                new Adopter(2,"Karina Mtz", "111-004-66",today.plusDays(5), TypePet.Cat),
                new Adopter(3,"Selene Hernandez", "700-010-66",today.plusDays(10), TypePet.Turtle));

        Mockito.when(adopterDAO.insert(adopterList.get(0))).thenReturn(adopterList.get(0));

        Adopter adopter = adopterService.addAdopter(adopterList.get(0));

        assertEquals(adopterList.get(0).getName(), "Melisa Lopez");

        Mockito.verify(adopterDAO).insert(adopterList.get(0));
    }

    @Test
    public void deleteAdopterTest() {
        int idAdopter = 1;

        //Solamente informativo
        //Adopter adopter = new Adopter(1,"Laura", "004 999 393", today.plusDays(15), TypePet.Cat);

        Mockito.when(adopterDAO.delete(idAdopter)).thenReturn(true);

        boolean result = adopterService.deleteAdopter(idAdopter);
        assertTrue(result);

        Mockito.verify(adopterDAO).delete(idAdopter);
    }


    @Test
    public void updateAdopterTest() {
        LocalDate today = LocalDate.now();
        Adopter adopter = new Adopter("Laura", "004 999 393", today.plusDays(15), TypePet.Cat);
        adopter.setId(2);

        Mockito.when(adopterDAO.update(adopter)).thenReturn(true);

        boolean result = adopterService.updateAdopter(adopter);
        assertTrue(result);

        Mockito.verify(adopterDAO).update(adopter);
    }

    @Test
    public void getAdopterIdTestOk() {
        LocalDate today = LocalDate.now();
        List<Adopter> adopterList = List.of(new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(15), TypePet.Dog),
                new Adopter(2,"Karina Mtz", "111-004-66",today.plusDays(5), TypePet.Cat),
                new Adopter(3,"Selene Hernandez", "700-010-66",today.plusDays(10), TypePet.Turtle));

        int id = 1;
        Mockito.when(adopterDAO.findById(id)).thenReturn(adopterList.get(0));
        Adopter result = adopterService.getAdopterById(1);
        assertNotNull(result);

        Mockito.verify(adopterDAO).findById(id);
    }

    @Test
    public void getAdopterIdTestFail() {
        LocalDate today = LocalDate.now();
        List<Adopter> adopterList = List.of(new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(15), TypePet.Dog),
                new Adopter(2,"Karina Mtz", "111-004-66",today.plusDays(5), TypePet.Cat),
                new Adopter(3,"Selene Hernandez", "700-010-66",today.plusDays(10), TypePet.Turtle));
        int id = 1000;

        Mockito.when(adopterDAO.findById(id)).thenReturn(null);
        Adopter adopter = adopterService.getAdopterById(id);
        assertNull(adopter);

        Mockito.verify(adopterDAO).findById(id);
    }

    @Test
    public void getAllAdopterTest(){
        LocalDate today = LocalDate.now();
        List<Adopter> adopterList = List.of(new Adopter(1,"Melisa Lopez", "111-004-66",today.plusDays(15), TypePet.Dog),
                new Adopter(2,"Karina Mtz", "111-004-66",today.plusDays(5), TypePet.Cat),
                new Adopter(3,"Selene Hernandez", "700-010-66",today.plusDays(10), TypePet.Turtle));
        Mockito.when(adopterDAO.findAll()).thenReturn(adopterList);

        List<Adopter> adopters = adopterService.getAllAdopters();
        adopters.forEach(System.out::println);

        assertEquals(3, adopters.size());

        Mockito.verify(adopterDAO).findAll();
    }

}
