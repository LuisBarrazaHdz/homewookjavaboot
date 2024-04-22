package expeditors.backend.config;

import expeditors.backend.adoption.Adopter;
import expeditors.backend.adoption.TypePet;
import expeditors.backend.dao.AdopterDAO;
import expeditors.backend.dao.inmemory.InMemoryAdopterDAO;
import expeditors.backend.dao.jpa.JpaAdopterDAO;
import expeditors.backend.service.AdopterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

//@Configuration
//@ComponentScan({"expeditors.backend", "expeditors.backend.application"})
public class AppConfig {

    /*
    @Bean
    @Profile("memory")
    public AdopterDAO adopterDAO() {
        var dao = new InMemoryAdopterDAO();
        List<Adopter> adopters = List.of(new Adopter(1,"Ana", "996-150-11",null, TypePet.Cat));
        adopters.forEach(dao::insert);

        return dao;
    }

    @Bean("adopterDAO")
    @Profile("jpa")
    public AdopterDAO jpaAdopterDAO() {
        var dao = new JpaAdopterDAO();
        List<Adopter> adopters = List.of(new Adopter(1,"Luis", "106-150-11",null, TypePet.Turtle));
        adopters.forEach(dao::insert);

        return dao;
    }

    @Bean
    public AdopterService adopterService(AdopterDAO adopterDAO) {
        AdopterService newService = new AdopterService();

        AdopterDAO dao = adopterDAO;

        newService.setAdopterDAO(dao);
        return newService;
    }
*/
}
